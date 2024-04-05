package org.sophanit.com.controller;

import org.sophanit.com.model.Venue;
import org.sophanit.com.model.request.RequestVenue;
import org.sophanit.com.model.response.ResponseApi;
import org.sophanit.com.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseApi<?>> getAllVenue(){
        List<Venue> allVenue = venueService.getAllVenue();
        ResponseApi<List<Venue>> responseApi = ResponseApi.<List<Venue>>builder()
                .message("Get all Successfully")
                .payload(allVenue)
                .status(HttpStatus.OK.toString())
                .time(new Date(System.currentTimeMillis()))
                .build();

        return ResponseEntity.ok(responseApi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> getVenueById(@PathVariable("id") Integer venue_id){

        Venue venue = venueService.getVenueById(venue_id);
        ResponseApi<Venue> responseApi = ResponseApi.<Venue>builder()
                .message("Get Venue By ID successfully")
                .payload(venue)
                .status(HttpStatus.FOUND.toString())
                .time(new Date(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(responseApi);
    }

    @PostMapping("")
    public ResponseEntity<ResponseApi<?>> postVenue(@RequestBody RequestVenue requestVenue){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> updateVenueById(@PathVariable("id") Integer venue_id){

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> deleteVenueById(@PathVariable("id") Integer venue_id){
        Integer storeId = venueService.deleteVenueById(venue_id);
        ResponseApi<?> responseApi = null;
        if (storeId!=null){
            responseApi = ResponseApi.builder()
                    .message("Delete Successfully")
                    .status(HttpStatus.OK.toString())
                    .time(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(responseApi);
        }
        return ResponseEntity.ok(ResponseApi.builder()
                .message("Delete no Success")
                .status(HttpStatus.NOT_FOUND.toString())
                .time(new Date(System.currentTimeMillis()))
                .build());
    }

}
