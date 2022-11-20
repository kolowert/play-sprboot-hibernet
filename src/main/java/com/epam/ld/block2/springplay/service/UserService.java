package com.epam.ld.block2.springplay.service;

import com.epam.ld.block2.springplay.exception.RecordNotFoundException;
import com.epam.ld.block2.springplay.model.UserEntity;
import com.epam.ld.block2.springplay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repo) {
        repository = repo;
    }

    public List<UserEntity> getAll() {
        List<UserEntity> result = (List<UserEntity>) repository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public UserEntity getUserById(long id) {
        Optional<UserEntity> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            String msg = String.format("No user record exist for given id %d", id);
            throw new RecordNotFoundException(msg);
        }
    }
}
