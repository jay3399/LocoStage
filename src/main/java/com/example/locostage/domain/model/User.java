package com.example.locostage.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
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
        private List<Review> reviews;

        @OneToMany(mappedBy = "user")
        private List<UserEvent> userEvents;

        @OneToMany(mappedBy = "user")
        private List<UserArtist> userArtists;

        // ... getters, setters, business methods ...


}
