package com.epam.ld.block2.springplay.repository;

import com.epam.ld.block2.springplay.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
