package com.example.mail_tracker.repositories;

import com.example.mail_tracker.models.ItemAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemActionsRepository extends JpaRepository<ItemAction, Integer> {

    Optional<ItemAction> findTopByItemIdOrderByActionTimestampDesc(int id);

}
