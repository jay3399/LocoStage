package com.example.locostage.domain.service;

import com.example.locostage.application.dto.EventDetailed;
import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.model.EventFestival;
import com.example.locostage.domain.model.Festival;
import com.example.locostage.domain.model.Venue;
import com.example.locostage.domain.repository.EventRepository;
import com.example.locostage.domain.repository.FestivalRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getLatestEventsByLocationV2(String country ) {

        List<Event> allEventsByCountry = eventRepository.findAllEventsByCountry(country);

        return allEventsByCountry;

    }

    public EventDetailed getEvent(Long eventID) {
        Event event = eventRepository.findById(eventID).orElseThrow(EntityNotFoundException::new);
        Venue venue = event.getVenue();

        Festival festival = null;
        EventFestival eventFestival = null;

        if (event instanceof EventFestival) {
            eventFestival = (EventFestival) event;
            festival = eventFestival.getFestival();
        }

        return EventDetailed.builder()
                .event(event)
                .EventFestival(eventFestival)
                .festival(festival)
                .venue(venue)
                .build();
    }


}
