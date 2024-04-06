package org.sophanit.com.service.serviceImplement;

import org.sophanit.com.model.Attendee;
import org.sophanit.com.model.request.AttendeeRequest;
import org.sophanit.com.repository.AttendeeRepo;
import org.sophanit.com.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImp implements AttendeeService {
    private final AttendeeRepo attendeeRepo;

    public AttendeeServiceImp(AttendeeRepo attendeeRepo) {
        this.attendeeRepo = attendeeRepo;
    }

    @Override
    public List<Attendee> getAllAttendees() {
        return attendeeRepo.getAllAttendees();
    }

    @Override
    public Attendee getAttendeeById(Integer id) {
        return attendeeRepo.getAttendeeById(id);
    }

    @Override
    public Integer postAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepo.postAttendee(attendeeRequest);
    }

    @Override
    public Integer deleteAttendeeById(Integer attendee_id) {
        return attendeeRepo.deleteAttendeeById(attendee_id);
    }

    @Override
    public Integer updateAttendeeById(Integer attendee_id, AttendeeRequest attendeeRequest) {
        return attendeeRepo.updateAttendeeById(attendee_id,attendeeRequest);
    }

}
