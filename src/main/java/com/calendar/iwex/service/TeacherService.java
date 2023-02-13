package com.calendar.iwex.service;

import com.calendar.iwex.entity.Teacher;
import com.calendar.iwex.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ExamService examService;
    public void createTeacher(String teacherName){
        Teacher teacher = new Teacher();
        teacher.setName(teacherName);
        Teacher teacherInDb = teacherRepository.save(teacher);

        examService.createExamTable(teacherInDb);
    }

    public List<Teacher> getAllTeacher(){
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacher(Long id){
        return teacherRepository.findById(id);
    }

    public void deleteTeacher(Long id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        teacherRepository.delete(teacher.get());
    }
}
