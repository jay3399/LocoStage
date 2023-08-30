package com.example.locostage.application.service;

import com.example.locostage.application.dto.EventListDTO;
import com.example.locostage.application.dto.mapper.EventListMapper;
import com.example.locostage.domain.service.EventService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventApplicationService {

    public final EventService eventService;


    @Transactional
    public List<EventListDTO> getLatestEventsV4(String country ){

        return EventListMapper.toDTOs(eventService.getLatestEventsByLocationV2(country));

    }
}


// 공연 상세 정보, 티켓 구매, 찜하기, 리뷰 관리 기능 제공.