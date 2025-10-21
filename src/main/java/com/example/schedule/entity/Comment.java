package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // n : 1 관계
    // 댓글은 여러개 스케쥴은 한 개
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private String content;
    private String writer;
    private String password;

    public Comment(Schedule schedule, String content, String writer, String password) {
        this.schedule = schedule;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }

    // 이거 뭐임
    public static Comment of(Schedule schedule, String content, String writer, String password){
        return new Comment(schedule, content, writer, password);
    }
}
