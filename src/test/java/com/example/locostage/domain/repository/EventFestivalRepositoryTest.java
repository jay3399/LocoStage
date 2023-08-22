package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.Artist;
import com.example.locostage.domain.model.Event;
import com.example.locostage.domain.model.EventFestival;
import com.example.locostage.domain.model.EventSole;
import com.example.locostage.domain.model.Festival;
import com.example.locostage.domain.model.Venue;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class EventFestivalRepositoryTest {


    @Autowired
    private FestivalRepository repository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventFestivalRepository eventFestivalRepository;

    @Autowired
    EventSoleRepository eventSoleRepository;

    @Test
    @Rollback(value = false)
    public void save() {

        Venue venue = new Venue("BayFrontPark" , "Miami , downtown" , "www.bayfrontpark.com" , "321-323-6645" , "12.23.12" , "US");

        Venue venue1 = new Venue("Lost Vally", "Waian , easttown", "www.azcawewd.com",
                "213-111-3333", "23.12.32.12", "UK");

        venueRepository.save(venue);
        venueRepository.save(venue1);

        Festival festival = new Festival("UMF", "일렉트로니카 페스티벌", LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(4), "https:ultramusicfestival.com", venue);

        Festival festival1 = new Festival("UMF AFTER FESTIVAL", "애프터페스티벌",
                LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(8), "qwqeqeq", venue);

        Festival festival2 = new Festival("CreamFileds", "크림필즈페스티벌",
                LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(4), "www.asdw.com",
                venue1);


        repository.save(festival);
        repository.save(festival1);
        repository.save(festival2);

        Artist artist = new Artist("Martin Garrix" , "no" , "The DJ that DJMag gets rank 1 in 2022");
        Artist artist1 = new Artist("Alesso" , "no" , "Progressive Dj ");

        artistRepository.save(artist);
        artistRepository.save(artist1);

        EventSole eventSole = new EventSole();
        eventSole.setEventName("MartinTour");
        eventSole.setDescription("단독");
        eventSole.setVenue(venue);
        eventSole.setArtist(artist);
        eventSole.setTicketLink("www.tiketmaster.com");
        eventSole.setDate(LocalDateTime.now());

        eventSoleRepository.save(eventSole);




        EventFestival eventFestival = new EventFestival();
        eventFestival.set(festival);
        eventFestival.setEventName(festival.getName());
        eventFestival.setTicketLink(festival.getTicketLink());
        eventFestival.setDate(festival.getStartDate());
        eventFestival.setVenue(venue);
        eventFestival.setArtist(artist);
        eventFestival.setDescription(festival.getDescription());
        eventFestivalRepository.save(eventFestival);

//        eventFestivalRepository.save(eventFestival);











    }

    @Test
    @Rollback(value = false)
    public void delete() {

        List<Event> all = eventRepository.findAll();

        for (Event event : all) {

            if (event instanceof EventFestival) {
                System.out.println(((EventFestival) event).getFestival());
                System.out.println(event.getEventName());
                System.out.println(event.getArtist().getName());
            }  else {
                System.out.println(event.getEventName());
                System.out.println(event.getArtist().getName());
            }

        }

//        List<Artist> artistByFestival = artistRepository.findArtistByFestival();
//
//        for (Artist artist : artistByFestival) {
//            System.out.println("artist.getName( = " + artist.getName());
//        }

//        List<Festival> us = repository.findByVenueCountryOrderByStartDate("US");
//
//        for (Festival u : us) {
//            System.out.println("us = " + u.getFestivalId());
//            System.out.println("u.getStartDate( = " + u.getStartDate());
//        }
//

//
//        List<Festival> festivalsByArtist = artistFestivalRepository.findFestivalsByArtist(1L);
//        System.out.println(festivalsByArtist.get(0).getName());
//
//        artistRepository.deleteAll();
//        venueRepository.deleteAll();



    }



}