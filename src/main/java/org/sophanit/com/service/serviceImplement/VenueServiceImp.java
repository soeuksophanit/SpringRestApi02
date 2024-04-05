package org.sophanit.com.service.serviceImplement;

import org.sophanit.com.model.Venue;
import org.sophanit.com.repository.VenueRepo;
import org.sophanit.com.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImp implements VenueService {
    private final VenueRepo venueRepo;

    public VenueServiceImp(VenueRepo venueRepo) {
        this.venueRepo = venueRepo;
    }

    @Override
    public List<Venue> getAllVenue() {
        return venueRepo.getAllVenues();
    }

    @Override
    public Venue getVenueById(Integer id) {
        return venueRepo.getVenueById(id);
    }

    @Override
    public Integer deleteVenueById(Integer id) {
        return venueRepo.deleteVenueById(id);
    }


}
