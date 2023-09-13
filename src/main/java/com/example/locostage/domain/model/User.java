package com.example.locostage.domain.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userId;

        @Column(unique = true, nullable = false)
        private String email;
        private String firstName = "";
        private String lastName = "";

        @Temporal(TemporalType.TIMESTAMP)
        private Date lastLoginDate;

        @Column(nullable = true)
        private String deviceInformation;

        @Column(nullable = true)
        private Integer loginAttempts;

        @Column(nullable = true)
        private String accountStatus;

        private String refreshToken;

        private boolean isNewUser;


        @OneToMany(mappedBy = "user")
        private List<Review> reviews = new ArrayList<>();

        @OneToMany(mappedBy = "user")
        private List<UserEvent> events = new ArrayList<>();

        @OneToMany(mappedBy = "user")
        private List<UserArtist> userArtists = new ArrayList<>();


        public static User create(String email , boolean isNew) {
                User user = new User();
                user.email = email;
                user.isNewUser = true;
                return user;
        }



}
