package com.calendar.iwex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Gruppa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "teacher_id",nullable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "gruppa",cascade = CascadeType.REMOVE)
    private Set<Exam> exams;


    @ManyToOne
    @JoinColumn(name = "archive_id")
    private Archive archive;

    // method to send the group to archive
    public void sendToArchive(LocalDate archiveDate) {
        Archive archive = new Archive(this.name, archiveDate);
        archive.getGroups().add(this);
        this.setArchive(archive);
    }
}
