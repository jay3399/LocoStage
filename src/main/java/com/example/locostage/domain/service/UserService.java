package com.example.locostage.domain.service;

import com.example.locostage.domain.model.User;
import com.example.locostage.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository repository;


    public User findByEmail(String email) {

        return repository.findByEmail(email);
    }

    public User findByRefresh(String refresh) {
        return repository.findByRefreshToken(refresh);
    }

    public void save(User user) {
        repository.save(user);
    }




}
