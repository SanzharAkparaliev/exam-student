package com.calendar.iwex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "teacher",cascade = CascadeType.REMOVE)
    private Set<Gruppa> gruppa;

    public TeacherModel toModel(){
        return TeacherModel.builder()
                .id(id)
                .name(name)
                .gruppa(gruppa)
                .build();
    }
}
