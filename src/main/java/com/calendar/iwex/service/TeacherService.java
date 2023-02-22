package com.calendar.iwex.service;

import com.calendar.iwex.entity.Teacher;
import com.calendar.iwex.entity.TeacherModel;
import com.calendar.iwex.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    }

    public List<TeacherModel> getAllTeacher(){
        return teacherRepository.findAll().stream()
                .map(Teacher::toModel).collect(Collectors.toList());
    }

    public Optional<Teacher> getTeacher(Long id){
        return teacherRepository.findById(id);
    }

    public void deleteTeacher(Long id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        teacherRepository.delete(teacher.get());
    }

    public void  updateTeacher(Teacher teacher){
        Teacher teacherInDb = teacherRepository.findById(teacher.getId()).get();
        teacherInDb.setName(teacher.getName());
        teacherRepository.save(teacherInDb);
    }
}
