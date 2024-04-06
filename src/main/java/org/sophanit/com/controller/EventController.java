package org.sophanit.com.controller;


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
        ResponseApi<Event> responseApi = ResponseApi.<Event>builder()
                .message("Get event by ID successfully")
                .payload(event)
                .status(HttpStatus.OK.toString())
                .time(new Date(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(responseApi);
    }


    @PostMapping("")
    public ResponseEntity<ResponseApi<Event>> postEvent(@RequestBody RequestEvent requestEvent){

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
        Integer storeId = eventService.getEventById(event_id).getEvent_id();
        ResponseApi<?> responseApi = null;
        if (storeId!=null){
            Integer deleteId = eventService.deleteEventById(storeId);
            if (deleteId!=null){
                responseApi = ResponseApi.builder()
                        .message("Deleted event successfully")
                        .status(HttpStatus.OK.toString())
                        .time(new Date(System.currentTimeMillis()))
                        .build();

                return ResponseEntity.ok(responseApi);
            }
        }else {
            responseApi = ResponseApi.builder()
                    .message("Deleted event not success")
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .time(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(responseApi);
        }

        return null;
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<Event>> updateEventById(@PathVariable("id") Integer event_id,@RequestBody RequestEvent requestEvent){
        Event event = eventService.getEventById(event_id);
        ResponseApi<Event> responseApi = null;
        if (event!=null){
            eventService.deleteEventAttendees(event.getEvent_id());
            eventService.updateEventById(event.getEvent_id(),requestEvent);
            eventService.addAttendees(event.getEvent_id(),requestEvent.getAttendeeId());

            responseApi = ResponseApi.<Event>builder()
                    .message("Update Successfully")
                    .payload(eventService.getEventById(event.getEvent_id()))
                    .status(HttpStatus.OK.toString())
                    .time(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(responseApi);
        }else {

            return  null;

        }

    }


}
