package com.example.mail_tracker.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "post_office")
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "office_index", unique = true)
    private int officeIndex;

    @Column(name = "office_name")
    private String officeName;

    @Column(name = "office_address", unique = true)
    private String officeAddress;

    @OneToMany(mappedBy = "office")
    private List<ItemAction> processedItems;

    public PostOffice() {
    }

    public PostOffice(int id, int officeIndex, String officeName, String officeAddress) {
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

    public int getOfficeIndex() {
        return officeIndex;
    }

    public void setOfficeIndex(int officeIndex) {
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

    public List<ItemAction> getProcessedItems() {
        return processedItems;
    }

    public void setProcessedItems(List<ItemAction> processedItems) {
        this.processedItems = processedItems;
    }
}
