package com.example.mail_tracker.DTO;

import com.example.mail_tracker.enums.ItemStatus;
import com.example.mail_tracker.validation.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MovingActionDTO {

    @Pattern(regexp = "\\d+", message = "Office id should contain only digits")
    @NotBlank(message = "Office id should not be null or empty")
    private String officeId;

    @ValueOfEnum(enumClass = ItemStatus.class)
    @NotBlank(message = "Status should not be null or empty")
    private String status;

    public MovingActionDTO() {
    }

    public MovingActionDTO(String officeId, String status) {
        this.officeId = officeId;
        this.status = status;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
