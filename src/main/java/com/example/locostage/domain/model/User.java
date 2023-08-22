package com.example.locostage.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userId;
        private String email;
        private String firstName = "";
        private String lastName = "";
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private LocalDateTime lastLoginDate;
        private String lastLoginDevice;
        private String authenticationToken;
        private LocalDateTime tokenExpirationDate;
        private String location;

        @OneToMany(mappedBy = "user")
        private List<Review> reviews = new ArrayList<>();

        @OneToMany(mappedBy = "user")
        private List<UserEvent> events = new ArrayList<>();

        @OneToMany(mappedBy = "user")
        private List<UserArtist> userArtists = new ArrayList<>();

        // ... getters, setters, business methods ...


}
