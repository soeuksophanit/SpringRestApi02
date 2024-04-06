package org.sophanit.com.repository;


import org.apache.ibatis.annotations.Mapper;
import org.sophanit.com.model.Attendee;

@Mapper
public interface EventAttendee {


    Attendee getAttendeeId(Integer id);
}
