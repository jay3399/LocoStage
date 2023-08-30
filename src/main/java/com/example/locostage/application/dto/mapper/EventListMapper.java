package com.example.locostage.application.dto.mapper;

import com.example.locostage.application.dto.EventListDTO;
import com.example.locostage.domain.model.Event;
import java.util.List;
import java.util.stream.Collectors;

public class EventListMapper {

    public static List<EventListDTO> toDTOs(List<Event> events) {
        return events.stream()
                .map(EventListMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static EventListDTO toDTO(Event event) {
        return EventListDTO.of(event);
    }


}
