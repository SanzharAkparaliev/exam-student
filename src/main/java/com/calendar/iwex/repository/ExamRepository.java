package com.calendar.iwex.repository;

import com.calendar.iwex.entity.Exam;
import com.calendar.iwex.entity.Gruppa;
import com.calendar.iwex.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
//    public List<Exam> findByGroup(Teacher teacher);
//
    public Exam findByAnn(String ann);

    public List<Exam> findByGruppa(Gruppa gruppa);
    public  void deleteByGruppaAndId(Gruppa gruppa,Long id);
}
