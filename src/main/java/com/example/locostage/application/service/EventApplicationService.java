package com.example.locostage.application.service;

import com.example.locostage.application.dto.EventDTO;
import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.service.EventService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventApplicationService {

    public final EventService eventService;

//    @Transactional
//    public List<EventDTO> getLatestEvents(int limit) {
//        return eventService.getLatestEvents(limit).stream().map(EventDTO::of)
//                .collect(Collectors.toList());
//    }

    @Transactional
    public List<EventDTO> getLatestEventsV2(int limit , String country) {

        return eventService.getLatestEventsByLocation(country, limit).stream().map(
                EventDTO::of
        ).collect(Collectors.toList());

    }






}


// 공연 상세 정보, 티켓 구매, 찜하기, 리뷰 관리 기능 제공.