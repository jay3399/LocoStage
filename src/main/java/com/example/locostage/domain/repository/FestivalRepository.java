package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.Festival;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {

    List<Festival> findByVenueCountryOrderByStartDate(String country , Pageable pageable);


}
