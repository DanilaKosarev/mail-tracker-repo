package com.example.mail_tracker.models;

import com.example.mail_tracker.enums.ItemStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "item_action")
public class ItemAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private PostalItem item;

    @ManyToOne
    @JoinColumn(name = "post_office_id", referencedColumnName = "id")
    private PostOffice office;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Column(name = "action_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionTimestamp;

    public ItemAction() {
    }

    public ItemAction(PostalItem item, PostOffice office, ItemStatus status, Date actionTimestamp) {
        this.item = item;
        this.office = office;
        this.status = status;
        this.actionTimestamp = actionTimestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PostalItem getItem() {
        return item;
    }

    public void setItem(PostalItem item) {
        this.item = item;
    }

    public PostOffice getOffice() {
        return office;
    }

    public void setOffice(PostOffice office) {
        this.office = office;
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
