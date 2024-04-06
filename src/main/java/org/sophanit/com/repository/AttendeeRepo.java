package org.sophanit.com.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.sophanit.com.model.Attendee;
import org.sophanit.com.model.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepo {

    @Select("SELECT * FROM attendees")
    List<Attendee> getAllAttendees();

    @Select("SELECT * FROM attendees WHERE attendees_id = #{id}")
    Attendee getAttendeeById(Integer id);

    @Select("SELECT a.attendees_id,a.attendees_name,a.email FROM event_attendee ea " +
            "INNER JOIN attendees a " +
            "ON ea.attendee_id = a.attendees_id " +
            "WHERE ea.event_id= #{id}")
    Attendee getAttendee(Integer id);


    @Select("INSERT INTO attendees VALUES (DEFAULT,#{req.attendees_name},#{req.email}) RETURNING attendees_id")
    Integer postAttendee(@Param("req") AttendeeRequest attendeeRequest);

    @Select("DELETE FROM attendees WHERE attendees_id = #{attendee_id} RETURNING attendees_id")
    Integer deleteAttendeeById(Integer attendee_id);

    @Select("UPDATE attendees SET attendees_name= #{req.attendees_name}," +
            "email= #{req.email}" +
            " WHERE attendees_id = #{attendee_id}" +
            " RETURNING attendees_id")
    Integer updateAttendeeById(Integer attendee_id,@Param("req") AttendeeRequest attendeeRequest);

}
