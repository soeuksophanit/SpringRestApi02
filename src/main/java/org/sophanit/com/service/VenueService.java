package org.sophanit.com.service;

import org.sophanit.com.model.Venue;
import org.sophanit.com.model.request.RequestVenue;

import java.util.List;

public interface VenueService {

    List<Venue> getAllVenue();

    Venue getVenueById(Integer id);

    Integer deleteVenueById(Integer id);

    Integer updateVenueById(RequestVenue requestVenue ,Integer id);

    Integer postNewVenue(RequestVenue requestVenue);

}
