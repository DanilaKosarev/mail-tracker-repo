package com.example.mail_tracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PostOfficeDTO {

    private int id;

    @Pattern(regexp = "\\d{6}", message = "Postal code should contain 6 digits")
    @NotBlank(message = "Office index should not be null or empty")
    private String officeIndex;

    @NotBlank(message = "Office name should not be null or empty")
    private String officeName;

    @NotBlank(message = "Address should not be null or empty")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, [A-Z]\\w+", message = "Address should be in this format: 'State, City, Street'")
    private String officeAddress;

    public PostOfficeDTO() {
    }

    public PostOfficeDTO(int id, String officeIndex, String officeName, String officeAddress) {
        this.id = id;
        this.officeIndex = officeIndex;
        this.officeName = officeName;
        this.officeAddress = officeAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfficeIndex() {
        return officeIndex;
    }

    public void setOfficeIndex(String officeIndex) {
        this.officeIndex = officeIndex;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }
}
