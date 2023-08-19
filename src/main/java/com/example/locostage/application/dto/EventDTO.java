package com.example.locostage.application.dto;

import com.example.locostage.domain.model.Event;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class EventDTO {

    private Long eventId;
    private String eventName;
    private String description;
    private LocalDateTime date;
    private String ticketLink;
    private String artistName;
    private String venueName;

    private void set(Event event) {
        this.eventName = event.getEventName();
        this.description = event.getDescription();
        this.date = event.getDate();
        this.ticketLink = event.getTicketLink();
        this.artistName = event.getArtist().getName();
        this.venueName = event.getVenue().getName();
    }

    public static EventDTO of(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.set(event);
        return eventDTO;
    }

}
