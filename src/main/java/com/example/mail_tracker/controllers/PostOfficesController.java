package com.example.mail_tracker.controllers;

import com.example.mail_tracker.DTO.PostOfficeDTO;
import com.example.mail_tracker.models.PostOffice;
import com.example.mail_tracker.services.PostOfficesService;
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
import java.util.stream.Collectors;

@Tag(name = "Post Offices controller")
@RestController
@RequestMapping("/api/offices")
public class PostOfficesController {

    private final PostOfficesService postOfficesService;

    private final ModelMapper mapper;

    @Autowired
    public PostOfficesController(PostOfficesService postOfficesService, ModelMapper mapper) {
        this.postOfficesService = postOfficesService;
        this.mapper = mapper;
    }

    @Operation(summary = "Method allows to save a new post office")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created new office"),
            @ApiResponse(responseCode = "400", description = "If provided values is invalid")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOffice(@RequestBody @Valid PostOfficeDTO officeDTO) {
        postOfficesService.save(convertPostOfficeDtoToPostOffice(officeDTO));
    }

    @Operation(summary = "Method returns information about an existing post office")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "If id is invalid")
    })
    @GetMapping("/{id}")
    public PostOfficeDTO getOfficeById(@PathVariable @Parameter(name = "id", description = "Post Office id", example = "1") int id) {
        return convertPostOfficeToPostOfficeDto(postOfficesService.findById(id));
    }

    @Operation(summary = "Method returns a list of all existing post offices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved")
    })
    @GetMapping
    public List<PostOfficeDTO> getAllOffices() {
        return postOfficesService.findAll().stream().map(this::convertPostOfficeToPostOfficeDto).collect(Collectors.toList());
    }

    private PostOffice convertPostOfficeDtoToPostOffice(PostOfficeDTO postOfficeDTO) {
        return mapper.map(postOfficeDTO, PostOffice.class);
    }

    private PostOfficeDTO convertPostOfficeToPostOfficeDto(PostOffice postOffice) {
        return mapper.map(postOffice, PostOfficeDTO.class);
    }
}
