package com.example.locostage.application.service;

import com.example.locostage.domain.model.User;
import com.example.locostage.domain.service.EmailService;
import com.example.locostage.domain.service.UserService;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationService {


    private final UserService userService;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;


    public Map<String ,String> registerOrLogin(String email , String clientDeviceInfo) {

        User user = userService.findByEmail(email);

        boolean reauthenticate = isReauthenticate(user ,clientDeviceInfo);

        Map<String, String> tokens = new HashMap<>();


        if (reauthenticate) {
            String token = jwtUtil.generateToken(email, "emailCheck");
            emailService.sendVerificationEmail(email, token);
            tokens.put("message", "email verification required");
        } else {

            String accessToken = jwtUtil.generateToken(user, "loginCheck");
            String refreshToken = jwtUtil.generateRefreshToken(user);

            user.setRefreshToken(refreshToken);
            userService.save(user);

            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);
        }
        return tokens;

    }

    private boolean isReauthenticate(User user , String clientDeviceInfo) {

        if (clientDeviceInfo != null && clientDeviceInfo.equals(user.getDeviceInformation())) {
            return true;
        }

        if (user == null) {
            return true;
        }

        if (user.getLastLoginDate() != null) {
            long diff = new Date().getTime() - user.getLastLoginDate().getTime();

            long diffHours = diff / (60 * 60 * 1000);

            if (diffHours < 24) {
                return false;
            }
        }

        return true;
    }

    public User findByRefreshToken(String refreshToken) {
        return userService.findByRefresh(refreshToken);
    }

}

//    사용자 프로필 관리, 아티스트 팔로우, 공연 및 페스티벌 찜 기능 제공.