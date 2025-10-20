package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GetScheduleResponse {
    private final Long id;
    private final String title;
    private final String description;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

}
