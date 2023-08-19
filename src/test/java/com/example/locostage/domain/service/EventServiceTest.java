package com.example.locostage.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.repository.EventRepository;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository repository;

    @Test
    public void getEventByIdTest() {
        Event event1 = new Event();
        event1.setEventName("Test Event 1");
        event1.setDate(LocalDateTime.now().minusDays(1));

        Event event2 = new Event();
        event2.setEventName("Test Event 2");
        event2.setDate(LocalDateTime.now());

        when(repository.findAll()).thenReturn(Arrays.asList(event1, event2));

        List<Event> latestEvents = eventService.getLatestEvents(1);
        assertThat(latestEvents.size()).isEqualTo(1);

        assertThat(latestEvents.get(0).getEventName()).isEqualTo("Test Event 2");



    }


}