package com.epam.ld.block2.springplay.repository;

import com.epam.ld.block2.springplay.model.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends CrudRepository<TicketEntity, Long> {

    List<TicketEntity> findAll();

    @Override
    Optional<TicketEntity> findById(Long aLong);
}
