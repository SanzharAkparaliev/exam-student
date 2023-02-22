package com.calendar.iwex.repository;

import com.calendar.iwex.entity.Gruppa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GruppaRepository extends JpaRepository<Gruppa,Long> {
    List<Gruppa> findByTeacher(String teacher);
}
