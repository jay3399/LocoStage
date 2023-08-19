package com.example.locostage.application.service;

import com.example.locostage.application.dto.FestivalDTO;
import com.example.locostage.domain.service.FestivalService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FestivalApplicationService {

    private final FestivalService festivalService;

    public List<FestivalDTO> getLatestFestivals(String country, int limit) {
      return   festivalService.getLatestFestivalsByCountry(country, limit).stream().map(
                FestivalDTO::of
        ).collect(Collectors.toList());
    }


}

  //  페스티벌 상세 정보, 참여 아티스트 목록 및 찜하기 기능 제공
