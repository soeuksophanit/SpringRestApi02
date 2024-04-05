package org.sophanit.com.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Venue {
    private Integer venueId;
    private String venueName;
    private String venueLocation;
}
