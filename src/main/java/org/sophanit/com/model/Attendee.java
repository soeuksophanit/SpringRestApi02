package org.sophanit.com.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Attendee {
    private Integer attendees_id;
    private String attendees_name;
    private String email;
}
