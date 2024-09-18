package com.example.mail_tracker.repositories;

import com.example.mail_tracker.models.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostOfficesRepository extends JpaRepository<PostOffice, Integer> {
}
