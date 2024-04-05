package org.sophanit.com.controller;


import org.sophanit.com.model.Event;
import org.sophanit.com.model.response.ResponseApi;
import org.sophanit.com.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
