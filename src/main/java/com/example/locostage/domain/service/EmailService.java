package com.example.locostage.domain.service;


import com.example.locostage.application.exception.custom.EmailException;
import com.example.locostage.application.service.JwtUtil;
import com.example.locostage.domain.model.User;
import com.example.locostage.domain.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RedisTemplate redisTemplate;
    private final JavaMailSender javaMailSender;

    private static final String MAIL_ADDRESS = "josw90@naver.com";


    @Async
    public void sendVerificationEmail(String email, String token) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setTo(email);
            helper.setFrom("joswxx@gmail.com");
            helper.setSubject("Email Verification");

            String htmlText = "<h3>Click the button below to verify your email:</h3>" +
                    "<a href='http://localhost:8080/verifyEmail?token=" + token + "'>" +
                    "<button style='padding: 10px; background-color: blue; color: white;'>Verify Email</button>"
                    +
                    "</a>";

            helper.setText(htmlText, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailException("이메일 전송에 실패했습니다", e);
        }


    }

    public String verifyEmail(String token) {

        String email = jwtUtil.validateToken(token).getSubject();

        User user = (User) redisTemplate.opsForValue().get("user:" + email);

        System.out.println("user = " + user);

        String storedEmail = (String) redisTemplate.opsForValue().get(token);

        System.out.println("storedEmail = " + storedEmail);

        if (storedEmail == null || !email.equals(storedEmail)) {
            return null;
        }

        if (user.isNewUser()) {
            user.setNewUser(false);
            userRepository.save(user);
        }

        return jwtUtil.generateToken(user, "loginCheck");
    }


}





