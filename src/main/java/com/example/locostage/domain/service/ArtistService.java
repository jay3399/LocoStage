package com.example.locostage.domain.service;

import com.example.locostage.domain.model.Artist;
import com.example.locostage.domain.repository.ArtistRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;


    public List<Artist> getArtistsByFestival(String country, Pageable pageable) {


        return artistRepository.findArtistByFestival(country, pageable);

    }

    public List<Artist> getArtistBySole(String country, Pageable pageable) {

        return artistRepository.findArtistBySole(country, pageable);
    }


}
