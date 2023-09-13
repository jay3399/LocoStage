package com.example.locostage.application.ui.controller;

import com.example.locostage.application.service.JwtUtil;
import com.example.locostage.application.service.UserApplicationService;
import com.example.locostage.application.ui.request.UserRequest;
import com.example.locostage.domain.model.User;
import com.example.locostage.domain.model.UserArtist;
import com.example.locostage.domain.service.EmailService;
import com.example.locostage.domain.service.UserService;
import jakarta.validation.Valid;
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


    // 가입 및 로그인 -> 이메일 입력창 -> 제출 ->1. 그대로 로그인
    // 2.신규유저인지  , 기존 회원이지만 이메일 인증시간 혹은 기기가 바뀌었을경우 경우를 구분해서 화면단에  기존회원일시에는 돌아오신걸 환영합니다 , 이메일인증을 해주세요 . 신규일시는 메일 가입인증 링크를 확인해주세요  ->
    // -> 2번시 , 이메일로들어가서 이메일 인증링크 누르면 새창뜨면서 인증이 완료되었습니다 알림창  ,  이전창에서 인증완료버튼 누르면 인증완료.


    @PostMapping("/registerOrLogin")
    public ResponseEntity<Map<String, String>> registerOrLogin(
            @RequestBody Map<String, String> payload,
//            @Valid @RequestBody UserRequest userRequest
            @RequestHeader("Device-Info") String clientDeviceInfo) {

        System.out.println("clientDeviceInfo = " + clientDeviceInfo);

        String email = payload.get("email");
        Map<String, String> tokens = userService.registerOrLogin(email, clientDeviceInfo);
        return ResponseEntity.ok(tokens);

    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        String accessToken = emailService.verifyEmail(token);

        if (accessToken != null) {
            String response = "<html><script>"
                    + "localStorage.setItem('loginToken', '" + accessToken + "');"
                    + "alert('인증이 완료되었습니다.');"
                    + "setTimeout(function() { window.location.href = '/mainPage'; }, 5000);"
                    + "</script></html>";
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증실패");
        }

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
