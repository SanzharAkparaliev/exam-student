package com.calendar.iwex.service;

import com.calendar.iwex.repository.ExamRepository;
import com.calendar.iwex.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//
//
//    public List<Exam> getAllExam(){
//       return examRepository.findAll();
//    }
//    public List<Exam> getExamByTeacher(Teacher teacher){
//        return examRepository.findByGroup(teacher);
//    }
//
//    public void deleteExam(Teacher teacher,Long examId){
////        examRepository.deleteByTeacherAndAndId(teacher,examId);
//    }
//
//    public void saveExam(Exam exam,Long groupId){
//        Optional<Group> group = groupRepository.findById(groupId);
//
//        exam.setGroup(group.get());
//        exam.setTotal(exam.getSpeaking() + exam.getWriting());
//        if(exam.getResult().equals("не сдал(а)")){
//            retakeService.createRateke(exam);
//        }
//        Manager manager = new Manager();
//        manager.setDate(exam.getDate());
//        manager.setLevel(exam.getLevel());
//        manager.setStudentName(exam.getStudentName());
//        manager.setAnn(exam.getAnn());
//        managerRepository.save(manager);
//        examRepository.save(exam);
//    }
//
//    public Optional<Exam> getExam(Long examId){
//       return   examRepository.findById(examId);
//    }
//
//    public void updateExam(Exam exam) {
//        Exam newExam = examRepository.findById(exam.getId()).get();
//        newExam.setDate(exam.getDate());
//        newExam.setComment(exam.getComment());
//        newExam.setLevel(exam.getLevel());
//        newExam.setResult(exam.getResult());
//        newExam.setCourseDates(exam.getCourseDates());
//        newExam.setSpeaking(exam.getSpeaking());
//        newExam.setWriting(exam.getWriting());
//        newExam.setTime(exam.getTime());
//        newExam.setAnn(exam.getAnn());
//        newExam.setTotal(exam.getSpeaking() + exam.getWriting());
//        if(exam.getResult().equals("не сдал(а)")){
//            Retake byRetakeByAnn = retakeService.findByRetakeByAnn(exam.getAnn());
//            if(byRetakeByAnn == null){
//                retakeService.createRateke(newExam);
//            }else {
//                byRetakeByAnn.setSpeaking(exam.getSpeaking());
//                byRetakeByAnn.setWriting(exam.getWriting());
//                byRetakeByAnn.setTotal(exam.getTotal());
//                retakeService.createRatekeByRetake(byRetakeByAnn);
//            }
//        }
//        examRepository.save(newExam);
//    }
//    public List<Manager> getAllStudent(){
//        return managerRepository.findAll();
//    }
//
//    public void updateExamRetake(Retake retake){
//        Exam exambyAnn = examRepository.findByAnn(retake.getAnn());
//        exambyAnn.setResult(retake.getResult());
//        exambyAnn.setTotal(retake.getTotal());
//        exambyAnn.setSpeaking(retake.getSpeaking());
//        exambyAnn.setWriting(retake.getWriting());
//        examRepository.save(exambyAnn);
//    }
}




