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
        List<UserEntity> result = repository.findAll();

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

    public List<UserEntity> getUsersByName(String name, int pageSize, int pageNum) {
        List<UserEntity> result = repository.findUserEntityByUsername(name);
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public UserEntity getUserByEmail(String email, int pageSize, int pageNum) {
        Optional<UserEntity> result = repository.findUserEntityByEmail(email);
        if (result.isPresent()) {
            return result.get();
        } else {
            String msg = String.format("No user record exist for given e-mail %s", email);
            throw new RecordNotFoundException(msg);
        }
    }

    public UserEntity createUser(UserEntity user) {
        if (user.getId() == null) {
            user = repository.save(user);
            return user;

        } else {
            Optional<UserEntity> userEntity = repository.findById(user.getId());
            if (userEntity.isPresent()) {
                String msg = String.format("User with id %d already exist", user.getId());
                throw new RecordNotFoundException(msg);
            }
        }

        String msg = String.format("Can't create User with predefined id %d", user.getId());
        throw new RecordNotFoundException(msg);
    }

    public boolean deleteUser(long id) {
        Optional<UserEntity> user = repository.findById(id);
        if(user.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            String msg = String.format("No user record exist for given id %d", id);
            throw new RecordNotFoundException(msg);
        }
    }

}
