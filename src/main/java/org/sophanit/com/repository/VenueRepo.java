package org.sophanit.com.repository;

import org.apache.ibatis.annotations.*;
import org.sophanit.com.model.Venue;
import org.sophanit.com.model.request.RequestVenue;

import java.util.List;


@Mapper
public interface VenueRepo {

    @Select("SELECT * FROM venues")
    @Results(
            id = "venue",
            value = {
                    @Result(property = "venueId",column = "venue_id"),
                    @Result(property = "venueName",column = "venue_name"),
                    @Result(property = "venueLocation",column = "location")
            }
    )
    List<Venue> getAllVenues();


    @Select("SELECT * FROM venues WHERE venue_id= #{id}")
    @ResultMap("venue")
    Venue getVenueById(Integer id);


    @Select("DELETE FROM venues WHERE venue_id = #{id} RETURNING venue_id")
    Integer deleteVenueById(Integer id);

    @Select("UPDATE venues SET venue_name = #{req.venueName}," +
            " location = #{req.venueLocation}" +
            " WHERE venue_id = #{id} " +
            " RETURNING venue_id")
    Integer updateVenueByID(@Param("req") RequestVenue requestVenue,Integer id);


    @Select("INSERT INTO venues VALUES (DEFAULT,#{req.venueName},#{req.venueLocation}) RETURNING venue_id")
    Integer postNewVenue(@Param("req") RequestVenue requestVenue);

}
