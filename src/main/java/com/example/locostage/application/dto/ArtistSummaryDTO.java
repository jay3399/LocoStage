package com.example.locostage.application.dto;

import com.example.locostage.domain.model.Artist;

public class ArtistSummaryDTO {

    private Long artistId;

    private String name;


    private void set(Artist artist) {
        this.artistId = artist.getArtistId();
        this.name = artist.getName();
    }

    public static ArtistSummaryDTO of(Artist artist) {
        ArtistSummaryDTO artistSummaryDTO = new ArtistSummaryDTO();
        artistSummaryDTO.set(artist);
        return artistSummaryDTO;
    }




}
