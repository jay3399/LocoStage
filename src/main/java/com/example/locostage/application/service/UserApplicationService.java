package com.example.locostage.application.service;

import com.example.locostage.domain.model.User;
import com.example.locostage.domain.service.EmailService;
import com.example.locostage.domain.service.UserService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationService {


    private final UserService userService;
    private final EmailService emailService;

    private final RedisTemplate redisTemplate;

    private final JwtUtil jwtUtil;


    public Map<String ,String> registerOrLogin(String email , String clientDeviceInfo) {

        // 여기서한번 -> 이메일 인증할떄 다시한번 -> 데이터접근두번 . 캐시로 막는다 .

        User user = userService.findByEmail(email).orElse(User.create(email));

        boolean reauthenticate = isReauthenticate(user ,clientDeviceInfo);

        Map<String, String> tokens = new HashMap<>();

        redisTemplate.opsForValue().set("user:" + email, user);


        if (reauthenticate) {
            String token = jwtUtil.generateToken(email, "emailCheck");

            redisTemplate.opsForValue().set(token, email);

            emailService.sendVerificationEmail(email, token);

            if (user.isNewUser()) {
                tokens.put("message", "환영합니다, 가입 인증메일을 보냈습니다 확인해주세요.");
            } else {
                tokens.put("message", "로그인 인증 메일을 보냈습니다 확인해주세요.");
            }


        } else {

            String accessToken = jwtUtil.generateToken(user, "loginCheck");
            String refreshToken = jwtUtil.generateRefreshToken(user);

            user.setRefreshToken(refreshToken);
            userService.save(user);

            tokens.put("loginToken", accessToken);
            tokens.put("refreshToken", refreshToken);
        }
        return tokens;

    }

    private boolean isReauthenticate(User user , String clientDeviceInfo) {

        if (user.isNewUser()) {
            return true;
        }

        if (isNewDevice(user, clientDeviceInfo)) {
            return true;
        }

        if (isLongtime(user)) {
            return true;
        }

        return false;
    }

    private static boolean isLongtime(User user) {
        if (user.getLastLoginDate() != null) {
            long diff = new Date().getTime() - user.getLastLoginDate().getTime();

            long diffHours = diff / (60 * 60 * 1000);

            if (diffHours > 24) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNewDevice(User user, String clientDeviceInfo) {
        if (clientDeviceInfo != null && clientDeviceInfo.equals(user.getDeviceInformation())) {
            return true;
        }
        return false;
    }

    public User findByRefreshToken(String refreshToken) {
        return userService.findByRefresh(refreshToken);
    }

}

//    사용자 프로필 관리, 아티스트 팔로우, 공연 및 페스티벌 찜 기능 제공.