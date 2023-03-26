package com.calendar.iwex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String groupName;

    @Column(nullable = false)
    private LocalDate archiveDate;

    @OneToMany(mappedBy = "archive")
    private Set<Gruppa> groups = new HashSet<>();

    public Archive(String groupName, LocalDate archiveDate) {
        this.groupName = groupName;
        this.archiveDate = archiveDate;
    }
}
