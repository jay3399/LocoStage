package com.example.locostage.application.dto;

import com.example.locostage.domain.model.Festival;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class FestivalDTO {

    private Long festivalId;
    private String name;
    private LocalDateTime startDate;

    private String venueName;

    private void set(Festival festival) {
        this.festivalId = festival.getFestivalId();
        this.name = festival.getName();
        this.startDate = festival.getStartDate();
        this.venueName = festival.getVenue().getName();
    }

    public static FestivalDTO of(Festival festival) {
        FestivalDTO festivalDTO = new FestivalDTO();
        festivalDTO.set(festival);
        return festivalDTO;
    }






}
