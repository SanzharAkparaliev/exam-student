package com.calendar.iwex.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time;
    private String date;

    private String studentName;
    private Integer speaking;
    private Integer writing;
    private String level;
    private String comment;
    private String result;
    private Integer total;
    private String CourseDates;
    private String ann;


    @ManyToOne
    @JoinColumn(name = "gruppa_id",nullable = false)
    private Gruppa gruppa;

}
