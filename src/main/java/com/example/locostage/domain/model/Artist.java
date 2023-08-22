package com.example.locostage.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;
    private String name;

    private String profileImage;
    private String description;

    public Artist(String name, String profileImage, String description) {
        this.name = name;
        this.profileImage = profileImage;
        this.description = description;
    }

//    @OneToMany(mappedBy = "artist")
//    private List<SoleEvent> soleEvents = new ArrayList<>();
//
//    @OneToMany(mappedBy = "artist")
//    private List<ArtistFestival> artistFestivals = new ArrayList<>();

    @OneToMany(mappedBy = "artist")
    private List<Event> events = new ArrayList<>();


}
