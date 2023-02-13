package com.calendar.iwex.service;

import com.calendar.iwex.entity.Manager;
import com.calendar.iwex.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    public List<Manager> searchManagerinfoByStudentName(String studentName){
        return managerRepository.findByKeyWord(studentName);
    }

    public List<Manager> searchManagerInfooByLevel(String level){
        return managerRepository.findByLevel(level);
    }
}
