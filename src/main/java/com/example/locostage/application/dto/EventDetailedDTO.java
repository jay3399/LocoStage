package com.example.locostage.application.dto;

import com.example.locostage.domain.model.Artist;
import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.model.Festival;
import com.example.locostage.domain.model.Venue;
import java.time.LocalDateTime;
import lombok.Setter;

@Setter
public class EventDetailedDTO {

    private String eventName;
    private String description;
    private LocalDateTime date;
    private String ticketLink;
    private String artistName;
    private String venueName;
    private Long artistId;
    private Long venueId;

    // Festival related information
    private Long festivalId;
    private String festivalName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private void set(Event event, Venue venue, Artist artist, Festival festival) {
        this.eventName = event.getEventName();
        this.description = event.getDescription();
        this.date = event.getDate();
        this.ticketLink = event.getTicketLink();
        this.artistName = artist.getName();
        this.venueName = venue.getName();
        this.artistId = artist.getArtistId();
        this.venueId = venue.getVenueId();

        if (festival != null) {
            this.festivalId = festival.getFestivalId();
            this.festivalName = festival.getName();
            this.startDate = festival.getStartDate();
            this.endDate = festival.getEndDate();
        }
    }

    public static EventDetailedDTO of(Event event, Venue venue, Artist artist, Festival festival) {
        EventDetailedDTO dto = new EventDetailedDTO();
        dto.set(event, venue, artist, festival);
        return dto;
    }





}
