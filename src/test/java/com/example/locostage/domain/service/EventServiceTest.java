package com.example.locostage.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.repository.EventRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @InjectMocks
    private EventService eventService;
    @Mock
    private EventRepository eventRepository;
    private List<Event> mockEvents;

    @BeforeEach
    public void setUp() {
        mockEvents = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Event event = new Event();
            event.setEventName("Test Event " + i);
            event.setDate(LocalDateTime.now().minusDays(i));
            mockEvents.add(event);
        }
        Pageable pageable = PageRequest.of(0, 3);

        when(eventRepository.findByVenueCountryOrderOrderByDateDesc("US", pageable)).thenReturn(
                mockEvents);
    }


    @Test
    public void testGetLatestPerformancesByCountry() {
        List<Event> events = eventService.getLatestEventsByLocation("US", 3);

        assertNotNull(events);
        assertEquals(3, events.size());

        for (int i = 0; i < events.size(); i++) {
            assertEquals("Test Event " + i, events.get(i).getEventName());
        }

        LocalDateTime previousDate = null;

        for (Event event : events) {
            if (previousDate == null) {
                previousDate = event.getDate();
                continue;
            }
            assertTrue(previousDate.isAfter(event.getDate()));
            previousDate = event.getDate();
        }

    }

    @AfterEach
    public void tearDown() {
        mockEvents.clear();
    }









    //@ExtendWith(MockitoExtension.class)
//    @InjectMocks
//    private EventService eventService;
//
//    @Mock
//    private EventRepository repository;



//    @Test
//    public void getEventLatestTest() {
//        Event event1 = new Event();
//        event1.setEventName("Test Event 1");
//        event1.setDate(LocalDateTime.now().minusDays(1));
//
//        Event event2 = new Event();
//        event2.setEventName("Test Event 2");
//        event2.setDate(LocalDateTime.now());
//
//        when(repository.findAll()).thenReturn(Arrays.asList(event1, event2));
//
//        List<Event> latestEvents = eventService.getLatestEvents(1);
//        assertThat(latestEvents.size()).isEqualTo(1);
//
//        assertThat(latestEvents.get(0).getEventName()).isEqualTo("Test Event 2");
//    }


}