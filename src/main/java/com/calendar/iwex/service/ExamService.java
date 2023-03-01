package com.calendar.iwex.service;

import com.calendar.iwex.entity.*;
import com.calendar.iwex.repository.ExamRepository;
import com.calendar.iwex.repository.GruppaRepository;
import com.calendar.iwex.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RetakeService retakeService;

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private GruppaRepository gruppaRepository;



    public List<Exam> getAllExam(){
       return examRepository.findAll();
    }
    public List<Exam> getExamByGroup(Gruppa gruppa){
        return examRepository.findByGruppa(gruppa);
    }

    public void deleteExam(Gruppa gruppa, Long examId){
        examRepository.deleteByGruppaAndId(gruppa,examId);
    }

    public void saveExam(Exam exam,Long groupId){
        Optional<Gruppa> gruppa = gruppaRepository.findById(groupId);

        exam.setGruppa(gruppa.get());
        exam.setTotal(exam.getSpeaking() + exam.getWriting());
        if(exam.getResult().equals("не сдал(а)")){
            retakeService.createRateke(exam);
        }
        Manager manager = new Manager();
        manager.setDate(exam.getDate());
        manager.setLevel(exam.getLevel());
        manager.setStudentName(exam.getStudentName());
        manager.setAnn(exam.getAnn());
        managerRepository.save(manager);
        examRepository.save(exam);
    }

    public Optional<Exam> getExam(Long examId){
       return   examRepository.findById(examId);
    }

    public void updateExam(Exam exam) {
        Exam newExam = examRepository.findById(exam.getId()).get();
        newExam.setDate(exam.getDate());
        newExam.setComment(exam.getComment());
        newExam.setLevel(exam.getLevel());
        newExam.setResult(exam.getResult());
        newExam.setCourseDates(exam.getCourseDates());
        newExam.setSpeaking(exam.getSpeaking());
        newExam.setWriting(exam.getWriting());
        newExam.setTime(exam.getTime());
        newExam.setAnn(exam.getAnn());
        newExam.setTotal(exam.getSpeaking() + exam.getWriting());
        newExam.setStudentName(exam.getStudentName());

        Retake byRetakeByAnn = retakeService.findByRetakeByAnn(exam.getAnn());

        if(exam.getResult().equals("не сдал(а)")){
            if(byRetakeByAnn == null){
                retakeService.createRateke(newExam);
            }else {
                byRetakeByAnn.setSpeaking(exam.getSpeaking());
                byRetakeByAnn.setWriting(exam.getWriting());
                byRetakeByAnn.setTotal(exam.getTotal());
                byRetakeByAnn.setAnn(exam.getAnn());
                byRetakeByAnn.setComment(exam.getComment());
                byRetakeByAnn.setLevel(exam.getLevel());
                byRetakeByAnn.setDate(exam.getDate());
                byRetakeByAnn.setTotal(exam.getSpeaking() + exam.getWriting());
                byRetakeByAnn.setTime(exam.getTime());
                byRetakeByAnn.setStudentName(exam.getStudentName());
                retakeService.createRatekeByRetake(byRetakeByAnn);
            }
        }
        examRepository.save(newExam);
    }
    public List<Manager> getAllStudent(){
        return managerRepository.findAll();
    }

    public void updateExamRetake(Retake retake){
        Exam exambyAnn = examRepository.findByAnn(retake.getAnn());
        exambyAnn.setResult(retake.getResult());
        exambyAnn.setTotal(retake.getTotal());
        exambyAnn.setSpeaking(retake.getSpeaking());
        exambyAnn.setWriting(retake.getWriting());
        exambyAnn.setDate(retake.getDate());
        exambyAnn.setLevel(retake.getLevel());
        exambyAnn.setComment(retake.getComment());
        exambyAnn.setAnn(retake.getAnn());
        exambyAnn.setTime(retake.getTime());
        exambyAnn.setStudentName(retake.getStudentName());
        examRepository.save(exambyAnn);
    }
}



