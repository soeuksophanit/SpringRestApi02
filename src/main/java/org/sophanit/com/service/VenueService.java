package org.sophanit.com.service;

import org.sophanit.com.model.Venue;

import java.util.List;

public interface VenueService {

    List<Venue> getAllVenue();

    Venue getVenueById(Integer id);

    Integer deleteVenueById(Integer id);

}
