package com.example.mail_tracker.services;

import com.example.mail_tracker.enums.ItemStatus;
import com.example.mail_tracker.exceptions.NoSuchPostOfficeException;
import com.example.mail_tracker.exceptions.NoSuchPostalItemException;
import com.example.mail_tracker.models.ItemAction;
import com.example.mail_tracker.models.PostOffice;
import com.example.mail_tracker.models.PostalItem;
import com.example.mail_tracker.repositories.ItemActionsRepository;
import com.example.mail_tracker.repositories.PostOfficesRepository;
import com.example.mail_tracker.repositories.PostalItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostalItemsService {

    private final PostalItemsRepository postalItemsRepository;

    private final ItemActionsRepository itemActionsRepository;

    private final PostOfficesRepository postOfficesRepository;

    @Autowired
    public PostalItemsService(PostalItemsRepository postalItemsRepository, ItemActionsRepository itemActionsRepository, PostOfficesRepository postOfficesRepository) {
        this.postalItemsRepository = postalItemsRepository;
        this.itemActionsRepository = itemActionsRepository;
        this.postOfficesRepository = postOfficesRepository;
    }

    @Transactional
    public void createItem(PostalItem postalItemToSave) {
        postalItemToSave.setId(0);
        postalItemsRepository.save(postalItemToSave);

        ItemAction itemAction = new ItemAction(
                postalItemToSave,
                null,
                ItemStatus.CREATED,
                new Date()
        );

        itemActionsRepository.save(itemAction);
    }

    @Transactional
    public void addAction(int itemId, int officeId, ItemStatus status) {

        PostalItem item = postalItemsRepository.findById(itemId).orElseThrow(() -> new NoSuchPostalItemException(itemId));
        PostOffice office = postOfficesRepository.findById(officeId).orElseThrow(() -> new NoSuchPostOfficeException(officeId));

        ItemAction itemAction = new ItemAction(
                item,
                office,
                status,
                new Date()
        );

        itemActionsRepository.save(itemAction);
    }

    @Transactional
    public void confirmDelivery(int itemId) {
        PostalItem item = postalItemsRepository.findById(itemId).orElseThrow(() -> new NoSuchPostalItemException(itemId));

        ItemAction itemAction = new ItemAction(
                item,
                null,
                ItemStatus.DELIVERED,
                new Date()
        );

        itemActionsRepository.save(itemAction);
    }

    public PostalItem findById(int id) {
        return postalItemsRepository.findById(id).orElseThrow(() -> new NoSuchPostalItemException(id));
    }

    public List<PostalItem> findAll() {
        return postalItemsRepository.findAll();
    }

    public ItemStatus findItemStatusById(int id) {
        return itemActionsRepository.findTopByItemIdOrderByActionTimestampDesc(id).orElseThrow(() -> new NoSuchPostalItemException(id)).getStatus();
    }

    public List<ItemAction> findItemHistoryById(int id) {
        List<ItemAction> listOfActions = postalItemsRepository.findById(id).orElseThrow(() -> new NoSuchPostalItemException(id)).getCompletedActions();
        listOfActions.sort(Comparator.comparing(ItemAction::getActionTimestamp));
        return listOfActions;
    }

}
