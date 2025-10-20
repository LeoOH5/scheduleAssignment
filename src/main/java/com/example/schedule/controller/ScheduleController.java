package com.example.schedule.controller;

import com.example.schedule.dto.*;
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

    @PutMapping("/schedules/{userName}")
    public UpdateScheduleResponse updateSchedule(@PathVariable String userName,@RequestBody UpdateScheduleRequest request){
        return scheduleService.updateSchedule(userName,request);
    }

    @DeleteMapping("/schedules/{id}")
    public void deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
    }

}

