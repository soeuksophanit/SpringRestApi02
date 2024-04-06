package org.sophanit.com.service;

import org.sophanit.com.model.Event;
import org.sophanit.com.model.request.RequestEvent;

import java.util.List;

public interface EventService {


    List<Event> getAllEvents();

    Event getEventById(Integer id);

    Integer postEvent(RequestEvent requestEvent);

    void addAttendees(Integer id,List<Integer> attendees_id);

    Integer deleteEventById(Integer event_id);

    Integer updateEventById(Integer id,RequestEvent requestEvent);

    void deleteEventAttendees(Integer id);
}
