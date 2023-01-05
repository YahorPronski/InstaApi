package com.company.notificationservice.event;

import lombok.Data;

import java.util.Date;

@Data
public class UserRegisteredEvent {

    private String username;
    private String email;
    private String firstName;
    private String lastName;

    private Date createdAt = new Date();
}
