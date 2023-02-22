package com.calendar.iwex.repository;

import com.calendar.iwex.entity.Exam;
import com.calendar.iwex.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
//    public List<Exam> findByGroup(Teacher teacher);
//
//    public Exam findByAnn(String ann);
}
