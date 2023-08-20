package com.example.locostage.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ArtistFestival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistFestivalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artistId")
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festivalId")
    private Festival festival;

    public ArtistFestival(Artist artist, Festival festival) {
        this.artist = artist;
        this.festival = festival;
    }
}
