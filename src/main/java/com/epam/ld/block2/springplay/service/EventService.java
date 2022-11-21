package com.epam.ld.block2.springplay.service;

import com.epam.ld.block2.springplay.exception.RecordNotFoundException;
import com.epam.ld.block2.springplay.exception.UndefinedOperationException;
import com.epam.ld.block2.springplay.model.EventEntity;
import com.epam.ld.block2.springplay.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EventService {

    private final EventRepository repository;

    @Autowired
    public EventService(EventRepository repo) {
        repository = repo;
    }

    public List<EventEntity> getAll() {
        List<EventEntity> result = repository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public EventEntity getEventById(long id) {
        Optional<EventEntity> event = repository.findById(id);
        if (event.isPresent()) {
            return event.get();
        } else {
            String msg = String.format("No event record exist for given id %d", id);
            throw new RecordNotFoundException(msg);
        }
    }

    public List<EventEntity> getEventsByTitle(String title, int pageSize, int pageNum) {
        return repository.findEventEntityByTitle(title);
    }

    public EventEntity createEvent(EventEntity event) {
        if (event.getId() == null) {
            event = repository.save(event);
            return event;

        } else {
            Optional<EventEntity> eventEntity = repository.findById(event.getId());
            if (eventEntity.isPresent()) {
                String msg = String.format("Event with id %d already exist", event.getId());
                throw new UndefinedOperationException(msg);
            }
        }

        String msg = String.format("Can't create Event with predefined id %d", event.getId());
        throw new UndefinedOperationException(msg);
    }

    public boolean deleteEvent(long id) {
        Optional<EventEntity> event = repository.findById(id);
        if(event.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            String msg = String.format("No event record exist for given id %d", id);
            throw new RecordNotFoundException(msg);
        }
    }
}
