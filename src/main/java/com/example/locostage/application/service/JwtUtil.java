package com.example.locostage.application.service;

import com.example.locostage.application.exception.custom.JwtTokenException;
import com.example.locostage.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {


    private final String secret;

    @Autowired
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }


    public  String generateToken(User user, String purpose) {

        try {
            return Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("userId", user.getUserId())
                    .claim("purpose", purpose)
                    .setExpiration(
                            new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();

        } catch (JwtException e) {

            throw new JwtTokenException("Login JWT 생성오류", e);

        }
    }

    public String generateToken(String email, String purpose) {

        try {

            return Jwts.builder()
                    .setSubject(email)
                    .claim("purpose", purpose)
                    .setExpiration(
                            new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();

        } catch (JwtException e) {

            throw new JwtTokenException("Email JWT 생성오류", e);

        }

    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getUserId())
                .claim("purpose", "refresh")
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)))  // 7일 동안 유효
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }



    public  Claims validateToken(String token) {

        try {

            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

        } catch (JwtException e) {

            throw new JwtTokenException("토큰 파싱오류", e);

        }
    }

    public Boolean isTokenExpired(HttpServletRequest request) {

        String token = extractToken(request);

        try {
            return getExpirationDateFromToken(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private Date getExpirationDateFromToken(String token) {

        return getClaimFromToken(token, Claims::getExpiration);

    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = gettAllClaimsFromToken(token);
        return claimsTFunction.apply(claims);
    }

    private Claims gettAllClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    private static String extractToken(HttpServletRequest request) {
        String token = getAuthorization(request);
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7); // "Bearer " 접두사를 제거합니다.
        }
        return null;
    }

    private static String getAuthorization(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }





}
