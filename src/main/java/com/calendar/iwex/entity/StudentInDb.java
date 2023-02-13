package com.calendar.iwex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentInDb {
    private Long id;
    private String surname;
    private String ann;

    @Override
    public String toString() {
        return "StudentInDb{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", ann='" + ann + '\'' +
                '}';
    }
}
