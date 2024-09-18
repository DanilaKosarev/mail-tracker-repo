package com.example.mail_tracker.repositories;

import com.example.mail_tracker.models.PostalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalItemsRepository extends JpaRepository<PostalItem, Integer> {
}
