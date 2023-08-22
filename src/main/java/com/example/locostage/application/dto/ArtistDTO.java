package com.example.locostage.application.dto;

import com.example.locostage.domain.model.Artist;
import com.example.locostage.domain.model.Event;
import lombok.Getter;

@Getter
public class ArtistDTO {

    private Long id;
    private String name;

    private void set(Artist artist) {
        this.id = artist.getArtistId();
        this.name = artist.getName();
    }

    public static ArtistDTO of(Artist artist) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.set(artist);
        return artistDTO;
    }


}

