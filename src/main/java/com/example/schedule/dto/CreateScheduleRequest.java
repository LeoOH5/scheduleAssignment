package com.example.schedule.dto;

import lombok.Getter;


@Getter
public class CreateScheduleRequest {
    private  String title;
    private String description;
    private String userName;
    private String password;
}
