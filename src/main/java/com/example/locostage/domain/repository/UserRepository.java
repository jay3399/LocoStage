package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, FestivalRepository> {


}
