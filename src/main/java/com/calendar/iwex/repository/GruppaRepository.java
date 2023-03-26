package com.calendar.iwex.repository;

import com.calendar.iwex.entity.Archive;
import com.calendar.iwex.entity.Gruppa;
import com.calendar.iwex.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GruppaRepository extends JpaRepository<Gruppa,Long> {
    List<Gruppa> findByTeacherAndArchive(Teacher teacher,Archive archive);

    Gruppa findByTeacherAndName(Teacher teacher,String name);
    List<Gruppa> findByArchive(Archive archive);
}
