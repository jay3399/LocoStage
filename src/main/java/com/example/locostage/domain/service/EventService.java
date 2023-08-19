package com.example.locostage.domain.service;

import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.repository.EventRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository repository;

//    public List<Event> getLatestEvents(int limit) {
//        return repository.findAll().stream().sorted(Comparator.comparing(Event::getDate).reversed())
//                .limit(limit).collect(
//                        Collectors.toList(
//                        ));
//    }


    public List<Event> getLatestEventsByLocation(String country, int limit) {

        PageRequest pageable = PageRequest.of(0, limit);

        return repository.findByVenueCountryOrderOrderByDateDesc(country, pageable);
    }





}
