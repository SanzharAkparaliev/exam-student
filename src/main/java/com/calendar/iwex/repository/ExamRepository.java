package com.calendar.iwex.repository;

import com.calendar.iwex.entity.Exam;
import com.calendar.iwex.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
    public List<Exam> findByTeacher(Teacher teacher);
    public void deleteByTeacherAndAndId(Teacher teacher,Long id);
    public Exam findByAnn(String ann);
}
