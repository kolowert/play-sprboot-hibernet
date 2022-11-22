package com.epam.ld.block2.springplay.repository;

import com.epam.ld.block2.springplay.model.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Long> {

    List<EventEntity> findAll();

    List<EventEntity> findEventEntityByTitle(String title);

}
