package com.calendar.iwex.service;

import com.calendar.iwex.entity.Exam;
import com.calendar.iwex.entity.Manager;
import com.calendar.iwex.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;


    public void saveManager(Exam exam){
        Manager student = managerRepository.getByAnn(exam.getAnn());
        if(student == null){
            Manager manager = new Manager();
            manager.setDate(exam.getDate());
            manager.setLevel(exam.getLevel());
            manager.setStudentName(exam.getStudentName());
            manager.setAnn(exam.getAnn());
            managerRepository.save(manager);
        }else {
            student.setLevel(exam.getLevel());
            managerRepository.save(student);
        }
    }
    public List<Manager> searchManagerinfoByStudentName(String studentName){
        return managerRepository.findByKeyWord(studentName);
    }
    public List<Manager> getAllStudent(){
        return managerRepository.findAll();
    }

    public List<Manager> searchManagerInfooByLevel(String level){
        return managerRepository.findByLevel(level);
    }
}
