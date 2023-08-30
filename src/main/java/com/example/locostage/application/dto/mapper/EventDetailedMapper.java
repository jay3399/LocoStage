package com.example.locostage.application.dto.mapper;

import com.example.locostage.application.dto.EventDetailed;
import com.example.locostage.application.dto.EventDetailedDTO;
import com.example.locostage.domain.model.Artist;
import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.model.Festival;
import com.example.locostage.domain.model.Venue;

public class EventDetailedMapper {


    public static EventDetailedDTO toDTO(EventDetailed eventDetailed) {
        Event event = eventDetailed.getEvent();
        Venue venue = eventDetailed.getVenue();
        Artist artist = event.getArtist();
        Festival festival = eventDetailed.getFestival();

        return EventDetailedDTO.of(event, venue, artist, festival);
    }



}
