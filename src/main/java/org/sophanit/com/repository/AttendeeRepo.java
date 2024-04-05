package org.sophanit.com.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.sophanit.com.model.Attendee;

@Mapper
public interface AttendeeRepo {

    @Select("SELECT * FROM attendees WHERE attendees_id = #{id}")
    Attendee getAttendeeById(Integer id);

    @Select("SELECT a.attendees_id,a.attendees_name,a.email FROM event_attendee ea " +
            "INNER JOIN attendees a " +
            "ON ea.attendee_id = a.attendees_id " +
            "WHERE ea.event_id= #{id}")
    Attendee getAttendee(Integer id);

}
