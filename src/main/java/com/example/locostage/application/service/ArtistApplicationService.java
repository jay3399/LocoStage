package com.example.locostage.application.service;

import com.example.locostage.domain.model.Artist;
import com.example.locostage.domain.service.ArtistService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistApplicationService {

    private final ArtistService artistService;


    public List<Artist> getAllArtists(String country) {

        PageRequest pageable = PageRequest.of(0, 5);

        List<Artist> artistBySole = artistService.getArtistBySole("US", pageable);
        List<Artist> artistByFestival = artistService.getArtistsByFestival("US", pageable);

        Set<Artist> artistSet = new HashSet<>();
        artistSet.addAll(artistBySole);
        artistSet.addAll(artistByFestival);

        return new ArrayList<>(artistSet);
    }






}


// 아티스트 정보 및 공연 일정 제공, 아티스트 팔로우 기능 제공.