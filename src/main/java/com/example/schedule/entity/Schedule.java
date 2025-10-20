package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
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


}
