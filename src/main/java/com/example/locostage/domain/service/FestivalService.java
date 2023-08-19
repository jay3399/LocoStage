package com.example.locostage.domain.service;

import com.example.locostage.domain.model.Festival;
import com.example.locostage.domain.repository.FestivalRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FestivalService {

    private final FestivalRepository repository;


    public List<Festival> getLatestFestivalsByCountry(String country, int limit) {
        PageRequest pageable = PageRequest.of(0, limit);

        return repository.findByVenueCountryOrderByStartDateDesc(country, pageable);

    }





}
