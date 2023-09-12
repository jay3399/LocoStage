package com.example.locostage.domain.service;

import com.example.locostage.domain.model.User;
import com.example.locostage.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository repository;


    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(repository.findByEmail(email)).orElseThrow(
                () -> new EntityNotFoundException("Entity not founded by email"));
    }

    public User findByRefresh(String refresh) {
        return Optional.ofNullable(repository.findByRefreshToken(refresh)).orElseThrow(
                () -> new EntityNotFoundException());
    }

    public void save(User user) {
        repository.save(user);
    }





}
