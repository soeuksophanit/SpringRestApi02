package org.sophanit.com.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEvent {
    private String event_name;
    private Date event_date;
    private Integer venue_id;
    private List<Integer> attendeeId;
}
