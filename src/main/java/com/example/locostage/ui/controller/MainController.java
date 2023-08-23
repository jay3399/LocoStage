package com.example.locostage.ui.controller;

import com.example.locostage.application.dto.ArtistDTO;
import com.example.locostage.application.dto.EventDTO;
import com.example.locostage.application.dto.FestivalDTO;
import com.example.locostage.application.service.ArtistApplicationService;
import com.example.locostage.application.service.EventApplicationService;
import com.example.locostage.application.service.FestivalApplicationService;
import com.example.locostage.infrastructure.external.LocationService;
import com.example.locostage.ui.request.LocationRequest;
import com.example.locostage.ui.response.MainPageResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping("/api/main")
public class MainController {

    private final EventApplicationService eventApplicationService;
    private final FestivalApplicationService festivalApplicationService;
    private final ArtistApplicationService artistApplicationService;
    private final Executor executor;

    public MainController(EventApplicationService eventApplicationService,
            FestivalApplicationService festivalApplicationService, ArtistApplicationService artistApplicationService,
            @Qualifier("customExecutor") Executor executor) {
        this.eventApplicationService = eventApplicationService;
        this.festivalApplicationService = festivalApplicationService;
        this.artistApplicationService = artistApplicationService;
        this.executor = executor;
    }

    @PostMapping
    public ResponseEntity<MainPageResponse> getMainPageDataWithCountry(String country , @RequestBody
            LocationRequest locationRequest)
            throws ExecutionException, InterruptedException, TimeoutException, IOException, URISyntaxException {

//        PageRequest pageable = PageRequest.of(0, 5);


        if (country == null && locationRequest != null) {

            country = LocationService.getCountryName(locationRequest);

        }

        final String effectiveCountry = country;
        System.out.println("effectiveCountry = " + effectiveCountry);


        CompletableFuture<List<EventDTO>> eventFuture = CompletableFuture.supplyAsync(
                () -> eventApplicationService.getLatestEventsV4("US"), executor);

        CompletableFuture<List<FestivalDTO>> festivalFuture = CompletableFuture.supplyAsync(
                () -> festivalApplicationService.getLatestFestivals("US"), executor
        );

        CompletableFuture<List<ArtistDTO>> artistFuture = CompletableFuture.supplyAsync(
                () -> artistApplicationService.getAllArtists("US") , executor
        );

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(eventFuture, festivalFuture , artistFuture);

        List<EventDTO> eventDTOS;
        List<FestivalDTO> festivalDTOS;
        List<ArtistDTO> artistDTOS;


        try {
            combinedFuture.join();
            eventDTOS = eventFuture.get(5, TimeUnit.SECONDS);
            festivalDTOS = festivalFuture.get(5, TimeUnit.SECONDS);
            artistDTOS = artistFuture.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw e;
        }

        for (EventDTO eventDTO : eventDTOS) {
            System.out.println("eventDTO.getArtistName( = " + eventDTO.getArtistName());
        }

        MainPageResponse mainPageResponse = new MainPageResponse(eventDTOS, festivalDTOS, artistDTOS);


        return ResponseEntity.ok(mainPageResponse);


    }

    @GetMapping("/reject")
    public ResponseEntity<MainPageResponse> getMainPageWithoutCountry() {

        System.out.println("거절!!");
        return null;

    }


    private String convertLocationToCountry(Userlocation userLocation) {
        return null;
    }


}
