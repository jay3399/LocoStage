package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.UserFestivalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository extends JpaRepository<UserFestivalEvent, Long> {

}
