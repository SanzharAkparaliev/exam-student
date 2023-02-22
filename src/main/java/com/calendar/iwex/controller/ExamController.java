package com.calendar.iwex.controller;

import com.calendar.iwex.entity.*;
import com.calendar.iwex.helper.Message;
import com.calendar.iwex.service.ExamService;
import com.calendar.iwex.service.RetakeService;
import com.calendar.iwex.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class ExamController {

    @Autowired
    private ExamService examService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RetakeService retakeService;
//
//
//    @GetMapping("/teacher/{id}")
//    public String GetExamPage(@PathVariable("id") Long id, Model model){
//        Optional<Teacher> teacher = teacherService.getTeacher(id);
//        List<Exam> examByTeacher = examService.getExamByTeacher(teacher.get());
//        List<Teacher> teachers = teacherService.getAllTeacher();
//        model.addAttribute("teachers",teachers);
//        model.addAttribute("examResults",examByTeacher);
//        model.addAttribute("title","Экзамен");
//        model.addAttribute("exam",new Exam());
//        model.addAttribute("teacherId",teacher.get().getId());
//        return "exam";
//    }
//
    @PostMapping("/save")
    public String saveStudentExamInfo(@RequestParam("groupId") Long id,
                                      @ModelAttribute("exam") Exam exam,
                                      @RequestParam("ann") String ann
                                      ){
        examService.saveExam(exam,id);
        return "redirect:/group/get" + id;
    }
//    @PostMapping("/update")
//    public String updateStudentExamInfo(@RequestParam("teacherId") Long id,
//                                      @ModelAttribute("exam") Exam exam,
//                                      @RequestParam("ann") String ann
//    ){
//        examService.updateExam(exam);
//        return "redirect:/teacher/" + id;
//    }

//
//
//    @GetMapping("/exam/result/delete/{id}/teacher/{tId}")
//    public String deleteExamResult(@PathVariable("id") Long studentId,
//                                   @PathVariable("tId")Long teacherId,Model model){
//        List<Teacher> teachers = teacherService.getAllTeacher();
//        model.addAttribute("teachers",teachers);
//        Optional<Teacher> teacher = teacherService.getTeacher(teacherId);
//        examService.deleteExam(teacher.get(),studentId);
//        return "redirect:/teacher/" + teacherId;
//    }
//
//    @GetMapping("/exam/result/edit/{eid}/teacher/{id}")
//    public String updateCategory(@PathVariable("eid") Long examId,@PathVariable("id") Long teacherId,Model model){
//        Optional<Exam> exam = examService.getExam(examId);
//        List<Teacher> teachers = teacherService.getAllTeacher();
//        model.addAttribute("teachers",teachers);
//        if(exam.isPresent()){
//            model.addAttribute("student",exam.get());
//            model.addAttribute("teacherId",teacherId);
//            return "updateStudentForm";
//        }else {
//            return "404";
//        }
//    }
//
//
//
//
    @GetMapping("/teacher/group/{id}")
    public String getStudentFormPage(Model model, HttpSession session, @PathVariable("id") Long groupId){
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        List<Retake> allRetake = retakeService.getAllRetake();
        model.addAttribute("teachers",teachers);
        model.addAttribute("title","Студент");
        model.addAttribute("exam",new Exam());
        model.addAttribute("examResults",allRetake);
        model.addAttribute("groupId",groupId);
        model.addAttribute("student",session.getAttribute("student"));
        return "studentForm";
    }
//
//    @GetMapping("/retake")
//    public String getRetakePage(Model model){
//        List<Teacher> teachers = teacherService.getAllTeacher();
//        List<Retake> allRetake = retakeService.getAllRetake();
//        model.addAttribute("teachers",teachers);
//        model.addAttribute("title","Пересдача");
//        model.addAttribute("exam",new Exam());
//        model.addAttribute("examResults",allRetake);
//
//        return "retake";
//    }
//
//    @GetMapping("/retake/edit/{id}")
//    public String updateRetake(@PathVariable("id") Long retakeId,Model model){
//        Optional<Retake> retake = retakeService.getRetake(retakeId);
//        List<Teacher> teachers = teacherService.getAllTeacher();
//        model.addAttribute("teachers",teachers);
//        if(retake.isPresent()){
//            model.addAttribute("student",retake.get());
//            return "updateRetake";
//        }else {
//            return "404";
//        }
//    }
//
//    @GetMapping("/retake/student/delete/{id}")
//    public String deleteRetakeStudent(@PathVariable("id") Long studentId,Model model){
//        List<Teacher> teachers = teacherService.getAllTeacher();
//        model.addAttribute("teachers",teachers);
//        retakeService.deleteRetaeke(studentId);
//        return "redirect:/retake";
//    }
//
//    @PostMapping("/updateRetake")
//    public String updateStudentExamInfo(@ModelAttribute("retake") Retake retake
//    ){
//        retakeService.updateRetake(retake);
//        return "redirect:/retake";
//    }
//
//    @PostMapping("/searchretake")
//    public String searchRetake(@RequestParam("studentName") String studentName,Model model){
//        List<Teacher> teachers = teacherService.getAllTeacher();
//
//        if(studentName!=null){
//            List<Retake> list = retakeService.searchRetakeResultByStudentName(studentName);
//            model.addAttribute("teachers",teachers);
//            model.addAttribute("title","Пересдача");
//            model.addAttribute("exam",new Exam());
//            model.addAttribute("examResults",list);
//            model.addAttribute("studentName",studentName);
//        }else {
//            List<Retake> allRetake = retakeService.getAllRetake();
//            model.addAttribute("teachers",teachers);
//            model.addAttribute("title","Пересдача");
//            model.addAttribute("exam",new Exam());
//            model.addAttribute("examResults",allRetake);
//        }
//
//        return "retake";
//    }
//
//    @PostMapping("/searchlevelbyretake")
//    public String searchRetakeByLevel(@RequestParam("level") String level,Model model){
//        List<Teacher> teachers = teacherService.getAllTeacher();
//
//        if(level!=null){
//            List<Retake> list = retakeService.searchRetakeResultByLevel(level);
//
//            model.addAttribute("teachers",teachers);
//            model.addAttribute("title","Пересдача");
//            model.addAttribute("exam",new Exam());
//            model.addAttribute("examResults",list);
//        }else {
//            List<Retake> allRetake = retakeService.getAllRetake();
//            model.addAttribute("teachers",teachers);
//            model.addAttribute("title","Пересдача");
//            model.addAttribute("exam",new Exam());
//            model.addAttribute("examResults",allRetake);
//        }
//
//        return "retake";
//    }

}
