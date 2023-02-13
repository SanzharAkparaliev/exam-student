package com.calendar.iwex.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clock;
    private String teacherName;
    private String gruppa;



    @ManyToOne
    @JoinColumn(name = "day_id",nullable = false)
    private Day day;



}
