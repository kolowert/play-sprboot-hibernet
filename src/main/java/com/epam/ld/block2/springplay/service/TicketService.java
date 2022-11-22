package com.epam.ld.block2.springplay.service;

import com.epam.ld.block2.springplay.exception.RecordNotFoundException;
import com.epam.ld.block2.springplay.model.TicketEntity;
import com.epam.ld.block2.springplay.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TicketService {

    private final TicketRepository repository;

    @Autowired
    public TicketService(TicketRepository repo) {
        repository = repo;
    }

    public List<TicketEntity> getAll() {
        List<TicketEntity> result = repository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public TicketEntity getTicketById(long id) {
        Optional<TicketEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            String msg = String.format("No ticket record exist for given id %d", id);
            throw new RecordNotFoundException(msg);
        }
    }

}
