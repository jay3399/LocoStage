package com.example.locostage.application.service;

import com.example.locostage.application.dto.FestivalDTO;
import com.example.locostage.application.dto.mapper.FestivalMapper;
import com.example.locostage.domain.service.FestivalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FestivalApplicationService {

    private final FestivalService festivalService;

    @Transactional
    public List<FestivalDTO> getLatestFestivals(String country) {

        return FestivalMapper.toDTOs(
                festivalService.getLatestFestivalsByCountry(country));

    }

}

  //  페스티벌 상세 정보, 참여 아티스트 목록 및 찜하기 기능 제공
