package com.example.schedule.controller;

import com.example.schedule.dto.CreateScheduleRequest;
import com.example.schedule.dto.CreateScheduleResponse;
import com.example.schedule.dto.GetScheduleResponse;
import com.example.schedule.entity.Schedule;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public CreateScheduleResponse create(@RequestBody CreateScheduleRequest request){
        return scheduleService.createSchedule(request);
    }

    @GetMapping("/schedules/{userName}")
    public GetScheduleResponse getSchedule(@PathVariable String userName){
        return scheduleService.getSchedule(userName);
    }

    @GetMapping("/schedules")
    public List<GetScheduleResponse> findAll(){
        return scheduleService.getAll();
    }
}
