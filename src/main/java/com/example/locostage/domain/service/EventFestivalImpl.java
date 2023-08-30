package com.example.locostage.domain.service;

import com.example.locostage.application.dto.EventDetailed;
import com.example.locostage.domain.repository.FestivalRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventFestivalImpl implements EventDetailedStrategy{

    private final FestivalRepository festivalRepository;

    @Override
    public EventDetailed getEventDetail(Long eventId) {
        List<EventDetailed> eventDetaileds = festivalRepository.findFestivalAndEventsByEventId(
                eventId);
        return eventDetaileds.isEmpty() ? EventDetailed.builder().build()
                : eventDetaileds.get(0);

    }
}
