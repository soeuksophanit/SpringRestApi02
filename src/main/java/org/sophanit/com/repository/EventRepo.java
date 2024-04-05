package org.sophanit.com.repository;

import org.apache.ibatis.annotations.*;
import org.sophanit.com.model.Event;

import java.util.List;

@Mapper
public interface EventRepo {

    @Select("SELECT * FROM events")
    @Result(property = "event_id",column = "event_id")
    @Result(property = "venueList",column = "venue_id",
    one = @One(select = "org.sophanit.com.repository.VenueRepo.getVenueById"))
    @Result(property = "attendeeList" ,column = "event_id",
    one = @One(select = "org.sophanit.com.repository.AttendeeRepo.getAttendee"))
    List<Event> getAllEvents();



}
