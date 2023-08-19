package com.example.locostage.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;
    private String name;

    private String profileImage;
    private String description;

    @OneToMany(mappedBy = "artist")
    private List<ArtistEvent> artistEvents;

    @OneToMany(mappedBy = "artist")
    private List<ArtistFestival> artistFestivals;

}
