package com.example.locostage.domain.service;


import com.example.locostage.application.service.JwtUtil;
import com.example.locostage.domain.model.User;
import com.example.locostage.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
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

    @Async
    public void sendVerificationEmail(String email, String token) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);


        try {

            helper.setTo(email);
            helper.setSubject("Email Verification");

            String htmlText = "<h3>Click the button below to verify your email:</h3>" +
                    "<a href='http://your-app.com/verifyEmail?token=" + token + "'>" +
                    "<button style='padding: 10px; background-color: blue; color: white;'>Verify Email</button>" +
                    "</a>";

            helper.setText(htmlText, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    public String verifyEmail(String token) {

        Claims cliams;

        User user;

        try {
            cliams = jwtUtil.validateToken(token);
        } catch (Exception e) {
            return null;
        }

        String email = cliams.getSubject();

        String storedEmail = (String) redisTemplate.opsForValue().get(token);

        if (storedEmail == null || !email.equals(storedEmail)) {
            return null;
        }

         user = (User) redisTemplate.opsForValue().getAndDelete("user" + email);

        if (user == null) {
             user = userRepository.findByEmail(email);
        }


        if (user == null) {
            user = new User();
            user.setEmail(email);
        }

        userRepository.save(user);

        redisTemplate.delete(token);

        return jwtUtil.generateToken(user , "loginCheck" );
    }



}





