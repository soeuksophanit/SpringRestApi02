package org.sophanit.com.repository;

import org.apache.ibatis.annotations.*;
import org.sophanit.com.model.Event;
import org.sophanit.com.model.request.RequestEvent;

import java.util.List;

@Mapper
public interface EventRepo {

    @Select("SELECT * FROM events")
    @Results(
            id = "event",
            value = {
                    @Result(property = "event_id",column = "event_id"),
                    @Result(property = "venueList",column = "venue_id",
                            one = @One(select = "org.sophanit.com.repository.VenueRepo.getVenueById")),
                    @Result(property = "attendeeList" ,column = "event_id",
                            one = @One(select = "org.sophanit.com.repository.AttendeeRepo.getAttendee"))
            }
    )

    List<Event> getAllEvents();



    @Select("SELECT * FROM events WHERE event_id= #{id}")
   @ResultMap("event")
    Event getEventById(Integer id);


    @Select("INSERT INTO events VALUES (DEFAULT,#{req.event_name},#{req.event_date},#{req.venue_id}) RETURNING event_id")
    Integer postEvent(@Param("req") RequestEvent requestEvent);


    @Select("INSERT INTO event_attendee VALUES (DEFAULT,#{id},#{attendee_id})")
    void addAttendees(Integer id,Integer attendee_id);


    @Select("DELETE FROM events WHERE event_id = #{event_id} RETURNING event_id")
    Integer deleteEventById(Integer event_id);

    @Select("UPDATE events SET event_name = #{req.event_name}," +
            " event_date= #{req.event_date}," +
            " venue_id= #{req.venue_id} " +
            "WHERE event_id= #{id} RETURNING event_id")
    Integer updateEventById(Integer id,@Param("req") RequestEvent requestEvent);


    @Select("DELETE FROM event_attendee WHERE event_id= #{event_id}")
    void deleteEventAttendees(Integer event_id);



}
