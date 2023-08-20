package com.example.locostage.ui.controller;

import com.example.locostage.application.dto.EventDTO;
import com.example.locostage.application.dto.FestivalDTO;
import com.example.locostage.application.service.EventApplicationService;
import com.example.locostage.application.service.FestivalApplicationService;
import com.example.locostage.ui.request.MainPageResponse;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final Executor executor;

    public MainController(EventApplicationService eventApplicationService,
            FestivalApplicationService festivalApplicationService,
            @Qualifier("customExecutor") Executor executor) {
        this.eventApplicationService = eventApplicationService;
        this.festivalApplicationService = festivalApplicationService;
        this.executor = executor;
    }

    @GetMapping
    public ResponseEntity<MainPageResponse> getMainPageData(String country, int limit , Userlocation userLocation)
            throws ExecutionException, InterruptedException, TimeoutException {

        if (country == null && userLocation != null) {

            country = convertLocationToCountry(userLocation);

        }

        final String effectiveCountry = country;


        CompletableFuture<List<EventDTO>> eventFuture = CompletableFuture.supplyAsync(
                () -> eventApplicationService.getLatestEventsV2(limit, effectiveCountry), executor);

        CompletableFuture<List<FestivalDTO>> festivalFuture = CompletableFuture.supplyAsync(
                () -> festivalApplicationService.getLatestFestivals(effectiveCountry, limit), executor
        );

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(eventFuture, festivalFuture);

        List<EventDTO> eventDTOS;
        List<FestivalDTO> festivalDTOS;

        try {
            combinedFuture.join();
            eventDTOS = eventFuture.get(5, TimeUnit.SECONDS);
            festivalDTOS = festivalFuture.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw e;
        }

        MainPageResponse mainPageResponse = new MainPageResponse(eventDTOS, festivalDTOS);
        return ResponseEntity.ok(mainPageResponse);


    }

    private String convertLocationToCountry(Userlocation userLocation) {
        return null;
    }


}
