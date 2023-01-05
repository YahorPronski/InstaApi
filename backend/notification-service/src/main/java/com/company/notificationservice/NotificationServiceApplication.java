package com.company.notificationservice;

import com.company.notificationservice.event.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "userRegister")
    public void handleKafkaEvent(UserRegisteredEvent event) {
        log.info("New user have registered (username: {} email: {} full name: {} {})",
                event.getUsername(), event.getEmail(), event.getFirstName(), event.getLastName());
        // send email notification
    }
}