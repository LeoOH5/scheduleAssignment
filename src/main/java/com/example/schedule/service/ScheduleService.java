package com.example.schedule.service;

import com.example.schedule.dto.CreateScheduleRequest;
import com.example.schedule.dto.CreateScheduleResponse;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 생성
    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request){
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getDescription(),
                request.getUserName(),
                request.getPassword()
        );

        Schedule result = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                result.getId(),
                result.getTitle(),
                result.getDescription(),
                result.getUserName(),
                result.getCreatedAt(),
                result.getModifiedAt()
        );
    }

}
