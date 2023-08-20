package com.example.locostage.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long festivalId;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String ticketLink;

    public Festival(String name, String description, LocalDateTime startDate, LocalDateTime endDate,
            String ticketLink, Venue venue) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketLink = ticketLink;
        this.artistFestivals = new ArrayList<>();
//        this.venue = venue;
        setVenue(venue);
    }

    private void setVenue(Venue venue) {
        this.venue = venue;
        venue.getFestivals().add(this);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venueId")
    private Venue venue;

    @OneToMany(mappedBy = "festival")
    private List<ArtistFestival> artistFestivals = new ArrayList<>();

}
