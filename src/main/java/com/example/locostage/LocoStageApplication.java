package com.example.locostage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

// 이메일이용 로그인서비스구현

// 찜하기 기능이용시 , 알림기능 이용가능


@SpringBootApplication
@EnableAsync
public class LocoStageApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocoStageApplication.class, args);
    }

}
