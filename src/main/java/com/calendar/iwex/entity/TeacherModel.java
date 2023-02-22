package com.calendar.iwex.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TeacherModel {
    private Long id;
    private String name;
    private Set<Gruppa> gruppa;
}
