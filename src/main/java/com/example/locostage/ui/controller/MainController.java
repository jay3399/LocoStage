package com.example.locostage.ui.controller;

import com.example.locostage.application.service.EventApplicationService;
import com.example.locostage.application.service.FestivalApplicationService;
import com.example.locostage.ui.request.MainPageResponse;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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








}
