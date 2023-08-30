package com.example.locostage.application.dto.mapper;

import com.example.locostage.application.dto.FestivalDTO;
import com.example.locostage.domain.model.Festival;
import java.util.List;
import java.util.stream.Collectors;

public class FestivalMapper {

    public static List<FestivalDTO> toDTOs(List<Festival> festivals) {
        return festivals.stream()
                .map(FestivalMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static FestivalDTO toDTO(Festival festival) {
        return FestivalDTO.of(festival);
    }

}
