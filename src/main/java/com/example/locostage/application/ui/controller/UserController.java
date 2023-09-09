package com.example.locostage.application.ui.controller;

import com.example.locostage.application.service.JwtUtil;
import com.example.locostage.application.service.UserApplicationService;
import com.example.locostage.domain.model.User;
import com.example.locostage.domain.model.UserArtist;
import com.example.locostage.domain.service.EmailService;
import com.example.locostage.domain.service.UserService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userService;

    private final EmailService emailService;

    private final JwtUtil jwtUtil;


    @PostMapping("/registerOrLogin")
    public ResponseEntity<Map<String, String>> registerOrLogin(
            @RequestBody Map<String, String> payload,
            @RequestHeader("Device-Info") String clientDeviceInfo) {
        String email = payload.get("email");
        Map<String, String> tokens = userService.registerOrLogin(email, clientDeviceInfo);
        return ResponseEntity.ok(tokens);
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam String token) {
        return emailService.verifyEmail(token);
    }


    @PostMapping("/refreshToken")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestParam String refreshToken) {
        User user = userService.findByRefreshToken(refreshToken);
        if (user != null) {
            String newAccessToken = jwtUtil.generateToken(user, "loginToken");
            Map<String, String> token = new HashMap<>();
            token.put("accessToken", newAccessToken);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
