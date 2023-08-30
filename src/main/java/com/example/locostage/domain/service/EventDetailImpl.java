package com.example.locostage.domain.service;

import com.example.locostage.application.dto.EventDetailed;
import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventDetailImpl implements EventDetailedStrategy {


    private final EventRepository eventRepository;


    @Override
    public EventDetailed getEventDetail(Long eventId) {

        Event event = eventRepository.findById(eventId).orElseThrow(EntityNotFoundException::new);

        return EventDetailed.builder()
                .EventFestival(null)
                .festival(null)
                .venue(event.getVenue())
                .build();
    }
}
