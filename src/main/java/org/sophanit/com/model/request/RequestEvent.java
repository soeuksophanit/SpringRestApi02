package org.sophanit.com.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEvent {
    private String event_name;
    private String event_date;
    private Integer venue_id;
}
