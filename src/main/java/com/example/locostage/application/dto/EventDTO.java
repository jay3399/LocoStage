package com.example.locostage.application.dto;

import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.model.EventFestival;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class EventDTO {

    private Long eventId;
    private LocalDateTime date;
    private String artistName;
    private String venueName;
    private Long festivalId;

    private void set(Event event) {
        this.eventId = event.getEventId();
        this.date = event.getDate();
        this.artistName = event.getArtist().getName();

        if (event instanceof EventFestival) {
            this.venueName = ((EventFestival) event).getFestival().getName();
            this.festivalId = ((EventFestival) event).getFestival().getFestivalId();
        } else {
            this.venueName = event.getVenue().getName();
            this.festivalId = null;
        }
    }

    public static EventDTO of(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.set(event);
        return eventDTO;
    }

}
