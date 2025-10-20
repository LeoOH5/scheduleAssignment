package com.example.schedule.service;

import com.example.schedule.dto.CreateScheduleRequest;
import com.example.schedule.dto.CreateScheduleResponse;
import com.example.schedule.dto.GetScheduleResponse;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    // 특정 스케쥴 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse getSchedule(String userName){
        Schedule schedule = scheduleRepository.findByUserName(userName).orElseThrow(
                () -> new IllegalStateException("해당 이름의 유저 없음")
        );

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 전체 스케쥴 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll(){
        List<Schedule> schedules = scheduleRepository.findAll(
                // 내림차순 정렬을 위한 JpaRepository 정렬 기능
                Sort.by(Sort.Direction.DESC, "modifiedAt")
        );
        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getDescription(),
                    schedule.getUserName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

}
