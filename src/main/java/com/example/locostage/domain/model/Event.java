//package com.example.locostage.domain.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import lombok.NoArgsConstructor;
//
//@Entity
//public class Event {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long eventId;
//    private String eventName;
//    private String description;
//    private LocalDateTime date;
//    private String ticketLink;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "artistId")
//    private Artist artist;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "festivalId")
//    private Festival festival;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "venueId")
//    private Venue venue;
//
//    @OneToMany(mappedBy = "performance")
//    private List<Review> reviews = new ArrayList<>();
//
//
//
//
//}
