package org.sophanit.com.service.serviceImplement;

import org.sophanit.com.model.Event;
import org.sophanit.com.model.request.RequestEvent;
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

    @Override
    public Event getEventById(Integer id) {
        return eventRepo.getEventById(id);
    }

    @Override
    public Integer postEvent(RequestEvent requestEvent) {
        return eventRepo.postEvent(requestEvent);
    }

    @Override
    public void addAttendees(Integer id,List<Integer> attendees_id) {
        for (Integer attendee_id :attendees_id){
            eventRepo.addAttendees(id,attendee_id);
        }
    }

    @Override
    public Integer deleteEventById(Integer event_id) {
        return eventRepo.deleteEventById(event_id);
    }

    @Override
    public Integer updateEventById(Integer id,RequestEvent requestEvent) {
        eventRepo.updateEventById(id,requestEvent);
        return eventRepo.getEventById(id).getEvent_id();
    }

    @Override
    public void deleteEventAttendees(Integer id) {
        eventRepo.deleteEventAttendees(id);
    }
}
