package com.example.mail_tracker.models;

import com.example.mail_tracker.enums.ItemType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "postal_item")
public class PostalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Column(name = "recipient_index")
    private int recipientIndex;

    @Column(name = "recipient_address")
    private String recipientAddress;

    @Column(name = "recipient_name")
    private String recipientName;

    @OneToMany(mappedBy = "item")
    private List<ItemAction> completedActions;

    public PostalItem() {
    }

    public PostalItem(int id, ItemType type, int recipientIndex, String recipientAddress, String recipientName) {
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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getRecipientIndex() {
        return recipientIndex;
    }

    public void setRecipientIndex(int recipientIndex) {
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

    public List<ItemAction> getCompletedActions() {
        return completedActions;
    }

    public void setCompletedActions(List<ItemAction> completedActions) {
        this.completedActions = completedActions;
    }
}
