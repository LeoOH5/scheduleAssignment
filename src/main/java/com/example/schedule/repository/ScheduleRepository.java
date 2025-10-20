package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // orElseThrow를 사용하기 위해 optional로 받음
    Optional<Schedule> findByUserName(String userName);
}
