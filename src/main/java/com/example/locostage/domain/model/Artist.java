package com.example.locostage.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Artist extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String profileImage;

    @Size(max = 400)
    private String description;

    public Artist(String name, String profileImage, String description) {
        this.name = name;
        this.profileImage = profileImage;
        this.description = description;
    }

    @OneToMany(mappedBy = "artist")
    private List<Event> events = new ArrayList<>();


}
