package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.Artist;
import com.example.locostage.domain.model.ArtistFestival;
import com.example.locostage.domain.model.Festival;
import com.example.locostage.domain.model.FestivalEvent;
import com.example.locostage.domain.model.Venue;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FestivalEventRepositoryTest {


    @Autowired
    private FestivalRepository repository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    ArtistFestivalRepository artistFestivalRepository;

    @Test
    @Rollback(value = false)
    public void save() {

        Venue venue = new Venue("BayFrontPark" , "Miami , downtown" , "www.bayfrontpark.com" , "321-323-6645" , "12.23.12" , "US");

        venueRepository.save(venue);

        Festival festival = new Festival("UMF", "일렉트로니카 페스티벌", LocalDateTime.now(),
                LocalDateTime.now().plusDays(1), "https:ultramusicfestival.com", venue);

//        Festival festival1 = new Festival("UMF AFTER FESTIVAL", "애프터페스티벌",
//                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), "qwqeqeq", venue);

        repository.save(festival);
//        repository.save(festival1);

        Artist artist = new Artist("Martin Garrix" , "no" , "The DJ that DJMag gets rank 1 in 2022");

        artistRepository.save(artist);

        ArtistFestival artistFestival = new ArtistFestival(artist, festival);
//        ArtistFestival artistFestival1 = new ArtistFestival(artist, festival1);

        artistFestivalRepository.save(artistFestival);
//        artistFestivalRepository.save(artistFestival1);






    }

    @Test
    @Rollback(value = false)
    public void delete() {

        List<Festival> festivalsByArtist = artistFestivalRepository.findFestivalsByArtist(1L);
        System.out.println(festivalsByArtist.get(0).getName());


    }



}