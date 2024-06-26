package org.sophanit.com.controller;

import org.sophanit.com.exception.ApiException;
import org.sophanit.com.model.Attendee;
import org.sophanit.com.model.request.AttendeeRequest;
import org.sophanit.com.model.response.ResponseApi;
import org.sophanit.com.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }


    @GetMapping("")
    public ResponseEntity<ResponseApi<List<Attendee>>> getAllAttendees(){
        ResponseApi<List<Attendee>> responseApi = ResponseApi.<List<Attendee>>builder()
                .message("Get all attendees")
                .payload(attendeeService.getAllAttendees())
                .status(HttpStatus.OK.toString())
                .time(new Date(System.currentTimeMillis()))
                .build();

        return ResponseEntity.ok(responseApi);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<Attendee>> getAttendeeById(@PathVariable("id") Integer attendee_id){
        Attendee attendee = attendeeService.getAttendeeById(attendee_id);
        if (attendee!=null){
            ResponseApi<Attendee> responseApi = ResponseApi.<Attendee>builder()
                    .message("Get Attendee by ID")
                    .payload(attendeeService.getAttendeeById(attendee_id))
                    .status(HttpStatus.OK.toString())
                    .time(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(responseApi);
        }else
            throw new ApiException("Not Found","Attendee ID "+ attendee_id + " was not found","attendees/"+attendee_id,HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<ResponseApi<Attendee>> postAttendee(@RequestBody AttendeeRequest attendeeRequest){
        if (attendeeRequest.getAttendees_name().isEmpty()
                && attendeeRequest.getEmail().isEmpty()
        || attendeeRequest.getAttendees_name().contains("string") && attendeeRequest.getEmail().contains("string")
                || !attendeeRequest.getEmail().contains("@gmail.com")
        )
            throw new ApiException("Empty Fields","Some fields might be empty","attendees",HttpStatus.BAD_REQUEST);
        ResponseApi<Attendee> responseApi = null;
        Integer storeId  = attendeeService.postAttendee(attendeeRequest);
        if (storeId!=null){
            responseApi = ResponseApi.<Attendee>builder()
                    .message("Adding new Attendee successfully")
                    .payload(attendeeService.getAttendeeById(storeId))
                    .status(HttpStatus.CREATED.toString())
                    .time(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(responseApi);
        }else return null;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> deleteAttendeeById(@PathVariable("id") Integer attendee_id){
        ResponseApi<?> responseApi = null;
        Attendee attendee = attendeeService.getAttendeeById(attendee_id);
        if (attendee!=null){
            Integer deleteId = attendeeService.deleteAttendeeById(attendee.getAttendees_id());
            responseApi = ResponseApi.builder()
                    .message("Delete Attendee ID "+deleteId + " Successfully")
                    .status(HttpStatus.OK.toString())
                    .time(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(responseApi);
        }else {
            throw new ApiException("Delete Not Found","Attendee ID "+ attendee_id + " was not found","attendees/"+attendee_id,HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<Attendee>>
    updateAttendeeById(@PathVariable("id") Integer attendee_id,
                       @RequestBody AttendeeRequest attendeeRequest){
        if (attendeeRequest.getAttendees_name().isEmpty()
                && attendeeRequest.getEmail().isEmpty()
                || attendeeRequest.getAttendees_name().contains("string") && attendeeRequest.getEmail().contains("string")
                || !attendeeRequest.getEmail().contains("@gmail.com")
        )
            throw new ApiException("Empty Fields","Some fields might be empty","attendees",HttpStatus.BAD_REQUEST);
        ResponseApi<Attendee> responseApi = null;
        Attendee attendee = attendeeService.getAttendeeById(attendee_id);
        if (attendee!=null){
            Integer updateAttendee = attendeeService
                    .updateAttendeeById(attendee_id,
                            attendeeRequest);
            responseApi = ResponseApi.<Attendee>builder()
                    .message("Update attendee ID "+ updateAttendee + " successfully")
                    .payload(attendeeService.getAttendeeById(updateAttendee))
                    .status(HttpStatus.CREATED.toString())
                    .time(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(responseApi);
        }else {
            throw new ApiException("Not Found to Update","Attendee ID "+ attendee_id + " was not found","attendees/"+attendee_id,HttpStatus.NOT_FOUND);
        }
    }
}
