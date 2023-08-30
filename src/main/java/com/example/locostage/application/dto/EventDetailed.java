package com.example.locostage.application.dto;

import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.model.EventFestival;
import com.example.locostage.domain.model.Festival;
import com.example.locostage.domain.model.Venue;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventDetailed {

    private Event event;
    private Festival festival;
    private EventFestival EventFestival;
    private Venue venue;

    public EventDetailed(Festival festival, EventFestival EventFestival, Venue venue) {
        this.festival = festival;
        this.EventFestival = EventFestival;
        this.venue = venue;
    }

//    private String eventName;
//    private String description;
//    private LocalDateTime date;
//    private String ticketLink;
//    private String artistName;
//    private String venue;


}
