package org.sophanit.com.controller;


import org.sophanit.com.exception.ApiException;
import org.sophanit.com.model.Event;
import org.sophanit.com.model.request.RequestEvent;
import org.sophanit.com.model.response.ResponseApi;
import org.sophanit.com.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("")
    public ResponseEntity<ResponseApi<List<Event>>> getAllEvents(){
        List<Event> eventList = eventService.getAllEvents();
        ResponseApi<List<Event>> responseApi = ResponseApi.<List<Event>>builder()
                .message("Get all events successfully")
                .payload(eventList)
                .status(HttpStatus.OK.toString())
                .time(new Date(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(responseApi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<Event>> getEventById(@PathVariable("id") Integer event_id){
        Event event = eventService.getEventById(event_id);
        if (event!=null){
            ResponseApi<Event> responseApi = ResponseApi.<Event>builder()
                    .message("Get event by ID successfully")
                    .payload(event)
                    .status(HttpStatus.OK.toString())
                    .time(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(responseApi);
        }else
            throw new ApiException("Not Found","Event ID "+ event_id + " was not found","events/"+event_id);


    }


    @PostMapping("")
    public ResponseEntity<ResponseApi<Event>> postEvent(@RequestBody RequestEvent requestEvent){
        if (requestEvent.getEvent_name().isEmpty()
                && requestEvent.getEvent_date().toString().isEmpty()
                && requestEvent.getVenue_id()==null || requestEvent.getVenue_id()==0
                || requestEvent.getEvent_name().contains("string")
                || requestEvent.getEvent_date().toString().contains("string")
        )
            throw new ApiException("Empty Fields","Some fields might be empty","events");
        Integer storeId = eventService.postEvent(requestEvent);
        eventService.addAttendees(storeId,requestEvent.getAttendeeId());
        ResponseApi<Event> responseApi = ResponseApi.<Event>builder()
                .message("Add new Event")
                .payload(eventService.getEventById(storeId))
                .status(HttpStatus.OK.toString())
                .time(new Date(System.currentTimeMillis()))
                .build();

        return ResponseEntity.ok(responseApi);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> deleteEventById(@PathVariable("id") Integer event_id){
        Event event = eventService.getEventById(event_id);
        ResponseApi<?> responseApi = null;
        if (event!=null){
            Integer deleteId = eventService.deleteEventById(event_id);
            if (deleteId!=null){
                responseApi = ResponseApi.builder()
                        .message("Deleted event successfully")
                        .status(HttpStatus.OK.toString())
                        .time(new Date(System.currentTimeMillis()))
                        .build();

                return ResponseEntity.ok(responseApi);
            }
        }else
            throw new ApiException("Not found to delete","Event ID "+ event_id+ " was not found","events/"+event_id);

        return null;
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<Event>> updateEventById(@PathVariable("id") Integer event_id,@RequestBody RequestEvent requestEvent) {
        Event event = eventService.getEventById(event_id);
        ResponseApi<Event> responseApi = null;
        if (event != null) {
            if (requestEvent.getEvent_name().isEmpty()
                    && requestEvent.getEvent_date().toString().isEmpty()
                    && requestEvent.getVenue_id()==null || requestEvent.getVenue_id()==0
                    || requestEvent.getEvent_name().contains("string")
                    || requestEvent.getEvent_date().toString().contains("string")
            )
                throw new ApiException("Empty Fields","Some fields might be empty","events");
            eventService.deleteEventAttendees(event.getEvent_id());
            eventService.updateEventById(event.getEvent_id(), requestEvent);
            eventService.addAttendees(event.getEvent_id(), requestEvent.getAttendeeId());

            responseApi = ResponseApi.<Event>builder()
                    .message("Update Successfully")
                    .payload(eventService.getEventById(event.getEvent_id()))
                    .status(HttpStatus.OK.toString())
                    .time(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(responseApi);
        } else {
            throw new ApiException("Event not found", "Event ID "+event_id+" was not found to update", "events/"+event_id);
        }
    }
}
