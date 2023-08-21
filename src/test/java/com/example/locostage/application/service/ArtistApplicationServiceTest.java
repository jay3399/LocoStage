package com.example.locostage.application.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.locostage.domain.model.Artist;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArtistApplicationServiceTest {

    @Autowired
    ArtistApplicationService applicationService;


    @Test
    public void test() {

        List<Artist> us = applicationService.getAllArtists("US");

        for (Artist u : us) {
            System.out.println("u.getName() = " + u.getName());
        }

    }




}