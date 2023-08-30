package com.example.locostage.ui.controller;

import com.example.locostage.application.service.EventApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    public final EventApplicationService applicationService;



}
