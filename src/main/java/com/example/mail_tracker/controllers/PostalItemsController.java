package com.example.mail_tracker.controllers;

import com.example.mail_tracker.DTO.ItemActionDTO;
import com.example.mail_tracker.DTO.MovingActionDTO;
import com.example.mail_tracker.DTO.PostalItemDTO;
import com.example.mail_tracker.enums.ItemStatus;
import com.example.mail_tracker.models.ItemAction;
import com.example.mail_tracker.models.PostalItem;
import com.example.mail_tracker.services.PostalItemsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Postal Items controller")
@RestController
@RequestMapping("/api/items")
public class PostalItemsController {

    private final PostalItemsService postalItemsService;

    private final ModelMapper mapper;

    @Autowired
    public PostalItemsController(PostalItemsService postalItemsService, ModelMapper mapper) {
        this.postalItemsService = postalItemsService;
        this.mapper = mapper;
    }

    @Operation(summary = "Method returns a list of all postal items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping
    public List<PostalItemDTO> findAll() {
        return postalItemsService.findAll().stream().map(this::convertPostalItemToPostalItemDto).collect(Collectors.toList());
    }

    @Operation(summary = "Method allows to register a new item with 'CREATED' status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved new item with 'CREATED' status"),
            @ApiResponse(responseCode = "400", description = "If provided values is invalid")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveItem(@RequestBody @Valid PostalItemDTO postalItemDTO) {
        postalItemsService.createItem(convertPostalItemDtoToPostalItem(postalItemDTO));
    }

    @Operation(summary = "Method returns an existing item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved item"),
            @ApiResponse(responseCode = "404", description = "If id is invalid")
    })
    @GetMapping("/{id}")
    public PostalItemDTO findItemById(@PathVariable @Parameter(name = "id", description = "Item id", example = "1") int id) {
        return convertPostalItemToPostalItemDto(postalItemsService.findById(id));
    }

    @Operation(summary = "Method returns an existing item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved item status"),
            @ApiResponse(responseCode = "404", description = "If id is invalid")
    })
    @GetMapping("/{id}/status")
    public Map<String, String> findItemStatusById(@PathVariable @Parameter(name = "id", description = "Item id", example = "1") int id) {
        return Map.of("status", postalItemsService.findItemStatusById(id).name());
    }

    @Operation(summary = "Method returns a complete moving history of item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved item history"),
            @ApiResponse(responseCode = "404", description = "If id is invalid")
    })
    @GetMapping("/{id}/history")
    public List<ItemActionDTO> findItemHistoryById(@PathVariable @Parameter(name = "id", description = "Item id", example = "1") int id) {
        return postalItemsService.findItemHistoryById(id).stream().map(this::convertItemActionToItemActionDto).collect(Collectors.toList());
    }

    @Operation(summary = "Method allows to add a new action to item moving history",
            description = "An item is determined by a path variable, while status and an id of corresponding post office is transmitted in JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added new record to item moving history"),
            @ApiResponse(responseCode = "400", description = "If provided values was invalid"),
            @ApiResponse(responseCode = "404", description = "If id is invalid")
    })
    @PatchMapping("/{itemId}")
    public void addAction(@PathVariable @Parameter(name = "itemId", description = "Item id", example = "1") int itemId, @RequestBody @Valid MovingActionDTO movingActionDTO) {
        postalItemsService.addAction(itemId, Integer.parseInt(movingActionDTO.getOfficeId()), ItemStatus.valueOf(movingActionDTO.getStatus()));
    }

    @Operation(summary = "Method allows to set status of an item to 'DELIVERED' and add a new record to item history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added new record to item moving history with 'DELIVERED' status"),
            @ApiResponse(responseCode = "404", description = "If id is invalid")
    })
    @PatchMapping("/{id}/confirm-delivery")
    public void confirmDelivery(@PathVariable @Parameter(name = "id", description = "Item id", example = "1") int id) {
        postalItemsService.confirmDelivery(id);
    }

    private PostalItem convertPostalItemDtoToPostalItem(PostalItemDTO postalItemDTO) {
        return mapper.map(postalItemDTO, PostalItem.class);
    }

    private PostalItemDTO convertPostalItemToPostalItemDto(PostalItem postalItem) {
        return mapper.map(postalItem, PostalItemDTO.class);
    }

    private ItemActionDTO convertItemActionToItemActionDto(ItemAction itemAction) {
        return mapper.map(itemAction, ItemActionDTO.class);
    }
}
