package com.example.locostage.application.dto;

import com.example.locostage.domain.model.back.FestivalEvent;
import java.util.List;
import java.util.stream.Collectors;

public class FestivalMapper {

    public static List<EventDTO> toDTOs(List<FestivalEvent> events) {
        return events.stream()
                .map(EventMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static EventDTO toDTO(FestivalEvent event) {
        return EventDTO.of(event);
    }


}
