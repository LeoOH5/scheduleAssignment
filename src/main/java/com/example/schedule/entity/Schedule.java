package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Schedule(String title, String description, String userName, String password) {
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.password = password;
    }

    private  String title;
    private String description;
    @Column(unique = true, nullable = false)
    private String userName;
    private String password;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void update(String title, String userName){
        this.title = title;
        this.userName = userName;
    }
}
