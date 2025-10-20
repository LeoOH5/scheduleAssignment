package com.example.schedule.service;

import com.example.schedule.dto.*;
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

    // 스케쥴 업데이트
    @Transactional
    public UpdateScheduleResponse updateSchedule(String userName, UpdateScheduleRequest request){
        Schedule schedule = scheduleRepository.findByUserName(userName).orElseThrow(
                () -> new IllegalStateException("해당 이름의 유저 없음")
        );

        schedule.update(
                request.getTitle(),
                request.getUserName()
        );

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getUserName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }


    // 스케쥴 삭제
    @Transactional
    public void deleteSchedule(Long Id){
        boolean existence = scheduleRepository.existsById(Id);

        if (!existence){
            throw new IllegalStateException("없는 스케쥴입니다.");
        }
        scheduleRepository.deleteById(Id);

    }

}
