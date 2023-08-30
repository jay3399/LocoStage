package com.example.locostage.domain.service;

import com.example.locostage.application.dto.EventDetailed;

public interface EventDetailedStrategy {

    EventDetailed getEventDetail(Long eventId);

}
