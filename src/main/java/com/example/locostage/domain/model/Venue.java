package com.example.locostage.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venueId;
    private String name;
    private String address;
    private String website;
    private String phoneNumber;
    private String location;
    private String country;

    public Venue(String name, String address, String website, String phoneNumber, String location,
            String country) {
        this.name = name;
        this.address = address;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.country = country;
    }

    @OneToMany(mappedBy = "venue" )
    private List<Festival> festivals = new ArrayList<>();

}
