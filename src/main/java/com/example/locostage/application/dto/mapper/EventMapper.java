package com.example.locostage.application.dto.mapper;

import com.example.locostage.application.dto.EventDTO;
import com.example.locostage.domain.model.Event;
import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {

    public static List<EventDTO> toDTOs(List<Event> events) {
        return events.stream()
                .map(EventMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static EventDTO toDTO(Event event) {
        return EventDTO.of(event);
    }


}
