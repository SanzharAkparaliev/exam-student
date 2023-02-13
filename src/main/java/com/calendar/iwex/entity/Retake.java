package com.calendar.iwex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Retake {
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
    private String ann;
    @Column(columnDefinition = "integer default 0")
    private Integer count;

}
