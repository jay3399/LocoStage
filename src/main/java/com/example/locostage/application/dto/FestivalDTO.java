package com.example.locostage.application.dto;

import com.example.locostage.domain.model.Festival;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FestivalDTO {

    private Long festivalId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<ArtistSummaryDTO> artists;

    private void set(Festival festival) {
        this.festivalId = festival.getFestivalId();
        this.name = festival.getName();
        this.startDate = festival.getStartDate();
        this.endDate = festival.getEndDate();
        this.artists = festival.getArtistFestivals().stream().map(af -> ArtistSummaryDTO.of(af.getArtist()))
                .collect(Collectors.toList());
    }

    public static FestivalDTO of(Festival festival) {
        FestivalDTO festivalDTO = new FestivalDTO();
        festivalDTO.set(festival);
        return festivalDTO;
    }






}
