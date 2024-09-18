package com.example.mail_tracker.DTO;

import com.example.mail_tracker.enums.ItemStatus;

import java.util.Date;

public class ItemActionDTO {

    private int id;

    private int postalItemId;

    private int postOfficeId;

    private ItemStatus status;

    private Date actionTimestamp;

    public ItemActionDTO() {
    }

    public ItemActionDTO(int id, int postalItemId, int postOfficeId, ItemStatus status, Date actionTimestamp) {
        this.id = id;
        this.postalItemId = postalItemId;
        this.postOfficeId = postOfficeId;
        this.status = status;
        this.actionTimestamp = actionTimestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostalItemId() {
        return postalItemId;
    }

    public void setPostalItemId(int postalItemId) {
        this.postalItemId = postalItemId;
    }

    public int getPostOfficeId() {
        return postOfficeId;
    }

    public void setPostOfficeId(int postOfficeId) {
        this.postOfficeId = postOfficeId;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Date getActionTimestamp() {
        return actionTimestamp;
    }

    public void setActionTimestamp(Date actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }
}
