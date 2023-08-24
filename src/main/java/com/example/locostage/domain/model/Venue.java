package com.example.locostage.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String address;
    @Size(max = 100)
    private String website;
    @Size(max = 20)
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
