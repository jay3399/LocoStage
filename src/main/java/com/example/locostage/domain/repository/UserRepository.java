package com.example.locostage.domain.repository;

import com.example.locostage.domain.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, FestivalRepository> {

    Optional<User> findByEmail(String email);

    User findByRefreshToken(String RefreshToken);


}
