package org.sophanit.com.service.serviceImplement;

import org.sophanit.com.model.Event;
import org.sophanit.com.repository.EventRepo;
import org.sophanit.com.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventServiceImp implements EventService {
    private final EventRepo eventRepo;

    public EventServiceImp(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepo.getAllEvents();
    }
}
