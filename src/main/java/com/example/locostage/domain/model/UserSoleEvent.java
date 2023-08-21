package com.example.locostage.domain.model;

import com.example.locostage.domain.model.back.SoleEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class UserSoleEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userEventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId")
    private SoleEvent soleEvent;

    private Boolean alert;


}
