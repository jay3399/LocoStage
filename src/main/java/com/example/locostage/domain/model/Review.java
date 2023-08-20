package com.example.locostage.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festivalEventId")
    private FestivalEvent festivalEvent;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soleEventId")
    private SoleEvent soleEvent;

    public void setUser(User user) {
        this.user = user;
        user.getReviews().add(this);
    }

    public void setFestivalEvent(FestivalEvent festivalEvent) {
        this.festivalEvent = festivalEvent;
        festivalEvent.getReviews().add(this);
    }

    public void setSoleEvent(SoleEvent soleEvent) {
        this.soleEvent = soleEvent;
        soleEvent.getReviews().add(this);
    }

    private Integer rating;
    private String comment;
    private LocalDateTime createdDate;

}
