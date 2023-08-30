package com.example.locostage.application.dto.mapper;

import com.example.locostage.application.dto.ArtistDTO;
import com.example.locostage.domain.model.Artist;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistMapper {

    public static List<ArtistDTO> toDTOs(List<Artist> artists) {
        return artists.stream()
                .map(ArtistMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static ArtistDTO toDTO(Artist artist) {
        return ArtistDTO.of(artist);
    }

}
