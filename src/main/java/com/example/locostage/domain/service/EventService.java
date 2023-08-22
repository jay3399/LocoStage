package com.example.locostage.domain.service;

import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.repository.EventRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

//
//    public List<FestivalEvent> getLatestEventsByLocation(String country, int limit) {
//
//        PageRequest pageable = PageRequest.of(0, limit);
//
//        return repository.findByVenueCountryOrderByDateDesc(country, pageable);
//    }

    public List<Event> getLatestEventsByLocationV2(String country ) {

        List<Event> allEventsByCountry = repository.findAllEventsByCountry(country);

        for (Event event : allEventsByCountry) {
            System.out.println("event.getEventName( = " + event.getEventName());
        }

        return allEventsByCountry;



    }





}
