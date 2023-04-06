package com.example.tour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class TourApplication implements WebMvcConfigurer{

    public static void main(String[] args) {
        SpringApplication.run(TourApplication.class, args);
    }

}
