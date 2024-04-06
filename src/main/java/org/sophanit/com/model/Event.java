package org.sophanit.com.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Integer event_id;
    private String event_name;
    private String event_date;
    private Venue venueList;
    private List<Attendee> attendeeList = new ArrayList<>();
}
