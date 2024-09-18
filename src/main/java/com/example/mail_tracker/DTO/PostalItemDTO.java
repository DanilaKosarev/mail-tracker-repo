package com.example.mail_tracker.DTO;

import com.example.mail_tracker.enums.ItemType;
import com.example.mail_tracker.validation.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PostalItemDTO {

    private int id;

    @ValueOfEnum(enumClass = ItemType.class)
    @NotBlank(message = "Type value should not be null or empty")
    private String type;

    @Pattern(regexp = "\\d{6}", message = "Postal code should contain 6 digits")
    @NotBlank(message = "Index should not be null or empty")
    private String recipientIndex;

    @NotBlank(message = "Address should not be null or empty")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, [A-Z]\\w+", message = "Address should be in this format: 'State, City, Street'")
    private String recipientAddress;

    @NotBlank(message = "Name should not be null or empty")
    private String recipientName;

    public PostalItemDTO() {
    }

    public PostalItemDTO(int id, String type, String recipientIndex, String recipientAddress, String recipientName) {
        this.id = id;
        this.type = type;
        this.recipientIndex = recipientIndex;
        this.recipientAddress = recipientAddress;
        this.recipientName = recipientName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecipientIndex() {
        return recipientIndex;
    }

    public void setRecipientIndex(String recipientIndex) {
        this.recipientIndex = recipientIndex;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
}
