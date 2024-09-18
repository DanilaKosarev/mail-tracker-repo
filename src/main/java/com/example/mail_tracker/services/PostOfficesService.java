package com.example.mail_tracker.services;

import com.example.mail_tracker.exceptions.NoSuchPostOfficeException;
import com.example.mail_tracker.models.PostOffice;
import com.example.mail_tracker.repositories.PostOfficesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostOfficesService {

    private final PostOfficesRepository postOfficesRepository;

    @Autowired
    public PostOfficesService(PostOfficesRepository postOfficesRepository) {
        this.postOfficesRepository = postOfficesRepository;
    }

    @Transactional
    public void save(PostOffice postOfficeToSave) {
        postOfficeToSave.setId(0);
        postOfficesRepository.save(postOfficeToSave);
    }

    public PostOffice findById(int id) {
        return postOfficesRepository.findById(id).orElseThrow(() -> new NoSuchPostOfficeException(id));
    }

    public List<PostOffice> findAll() {
        return postOfficesRepository.findAll();
    }
}
