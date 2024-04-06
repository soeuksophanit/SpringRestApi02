package org.sophanit.com.service;

import org.sophanit.com.model.Attendee;
import org.sophanit.com.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {

    List<Attendee> getAllAttendees();
    Attendee getAttendeeById(Integer id);

    Integer postAttendee(AttendeeRequest attendeeRequest);

    Integer deleteAttendeeById(Integer attendee_id);

    Integer updateAttendeeById(Integer attendee_id,AttendeeRequest attendeeRequest);
}
