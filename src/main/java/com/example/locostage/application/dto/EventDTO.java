package com.example.locostage.application.dto;

import com.example.locostage.domain.model.back.FestivalEvent;
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

    private void set(FestivalEvent festivalEvent) {
        this.eventName = festivalEvent.getEventName();
        this.description = festivalEvent.getDescription();
        this.date = festivalEvent.getDate();
        this.ticketLink = festivalEvent.getTicketLink();
        this.artistName = festivalEvent.getArtist().getName();
        this.venueName = festivalEvent.getVenue().getName();
    }

    public static EventDTO of(FestivalEvent festivalEvent) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.set(festivalEvent);
        return eventDTO;
    }

}
