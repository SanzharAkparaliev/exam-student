package com.calendar.iwex.service;

import com.calendar.iwex.entity.*;
import com.calendar.iwex.repository.ExamRepository;
import com.calendar.iwex.repository.GruppaRepository;
import com.calendar.iwex.repository.TeacherRepository;
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
    private GruppaRepository gruppaRepository;
    @Autowired
    private ManagerService managerService;

    @Autowired
    private TeacherRepository teacherRepository;

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
        Exam byStudentName = examRepository.findByStudentName(exam.getStudentName());
        if(!(byStudentName == null)){
            examRepository.delete(byStudentName);
            Optional<Gruppa> gruppa = gruppaRepository.findById(groupId);
            exam.setGruppa(gruppa.get());
            exam.setTotal(exam.getSpeaking() + exam.getWriting());
            if(exam.getResult().equals("не сдал(а)")){
                retakeService.createRateke(exam);
            }else {
                managerService.saveManager(exam);
            }
            examRepository.save(exam);
        }else {
            Optional<Gruppa> gruppa = gruppaRepository.findById(groupId);
            exam.setGruppa(gruppa.get());
            exam.setTotal(exam.getSpeaking() + exam.getWriting());
            if(exam.getResult().equals("не сдал(а)")){
                retakeService.createRateke(exam);
            }else {
                managerService.saveManager(exam);
            }
            examRepository.save(exam);
        }

    }

    public Optional<Exam> getExam(Long examId){
       return   examRepository.findById(examId);
    }

    public void updateExam(Exam exam,String teacher,String group) {
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
        Retake byRetakeByAnn = retakeService.findByRetakeByAnn(exam.getStudentName());
        if(!(byRetakeByAnn == null)){
            byRetakeByAnn.setResult(newExam.getResult());
        }
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
//                retakeService.updateRetake(byRetakeByAnn);
            }
        }
        else {
            retakeService.updateRetake(byRetakeByAnn);
            managerService.saveManager(exam);
        }
        if (teacher != null && group != null) {
            Teacher teacherByName = teacherRepository.getById(Long.valueOf(teacher));
            Gruppa byTeacherAndName = gruppaRepository.findByTeacherAndName(teacherByName, group);
            newExam.setGruppa(byTeacherAndName);
        }
        examRepository.save(newExam);
    }


    public void updateExamRetake(Retake retake){
        try {
            Exam exambyAnn = examRepository.findByStudentName(retake.getStudentName());
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
            managerService.saveManager(exambyAnn);

            examRepository.save(exambyAnn);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



