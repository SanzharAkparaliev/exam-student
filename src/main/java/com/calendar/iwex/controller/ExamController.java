package com.calendar.iwex.controller;

import com.calendar.iwex.entity.*;
import com.calendar.iwex.helper.Message;
import com.calendar.iwex.service.ExamService;
import com.calendar.iwex.service.GruppaService;
import com.calendar.iwex.service.RetakeService;
import com.calendar.iwex.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    @Autowired
    private GruppaService gruppaService;

    @PostMapping("/save")
    public String saveStudentExamInfo (  @RequestParam("groupId") Long id, @ModelAttribute("exam") Exam exam,
                                       @RequestParam("ann") String ann, @Valid HttpSession session, Model model, BindingResult result){
        try {
            if(result.hasErrors()){
                model.addAttribute("exam",exam);
                session.setAttribute("msg",new Message("Мыкдай ID менен студент катталган  :) Туура маалымат киргизиниз !!","alert-danger"));
                return "redirect:/teacher/group/" + id;
            }
            examService.saveExam(exam,id);
            return "redirect:/group/get/" + id;
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("exam",exam);
            session.setAttribute("msg",new Message("Мыкдай ID менен студент катталган :) Туура маалымат киргизиниз !!","alert-danger"));
            return "redirect:/teacher/group/" + id;
        }
    }
    @PostMapping("/update")
    public String updateStudentExamInfo(@RequestParam("groupId") Long id,
                                      @ModelAttribute("exam") Exam exam,
                                      @RequestParam("ann") String ann,
                                        @RequestParam(value = "teacher",required = false)String teacher,
                                        @RequestParam(value = "group",required = false) String group
    ){
        System.out.println("Teacher = " + teacher + "  group" +group);
        examService.updateExam(exam,teacher,group);
        return "redirect:/group/get/" + id;
    }



    @GetMapping("/exam/result/delete/{id}/group/{gId}")
    public String deleteExamResult(@PathVariable("id") Long studentId,
                                   @PathVariable("gId")Long groupId,Model model){
        Optional<Gruppa> gruppaById = gruppaService.getGruppaById(groupId);
        examService.deleteExam(gruppaById.get(),studentId);
        return "redirect:/group/get/" + groupId;
    }

    @GetMapping("/exam/result/edit/{id}/gruppa/{gId}")
    public String updateCategory(@PathVariable("id") Long examId,@PathVariable("gId") Long gruppaId,Model model){
        Optional<Exam> exam = examService.getExam(examId);

        List<TeacherModel> teachers = teacherService.getAllTeacher();
        model.addAttribute("teachers",teachers);
        if(exam.isPresent()){
            model.addAttribute("student",exam.get());
            model.addAttribute("groupId",gruppaId);
            model.addAttribute("title",exam.get().getStudentName());
            List<Gruppa> allGruppa = gruppaService.getAllGruppa();
            model.addAttribute("allGruppa",allGruppa);
            return "updateStudentForm";
        }else {
            return "404";
        }
    }




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

        List<Gruppa> allGruppa = gruppaService.getAllGruppa();
        model.addAttribute("allGruppa",allGruppa);
        return "studentForm";
    }

    @GetMapping("/retake")
    public String getRetakePage(Model model){
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        List<Retake> allRetake = retakeService.getAllRetake();
        model.addAttribute("teachers",teachers);
        model.addAttribute("title","Пересдача");
        model.addAttribute("exam",new Exam());
        model.addAttribute("examResults",allRetake);
        List<Gruppa> allGruppa = gruppaService.getAllGruppa();
        model.addAttribute("allGruppa",allGruppa);

        return "retake";
    }

    @GetMapping("/retake/edit/{id}")
    public String updateRetake(@PathVariable("id") Long retakeId,Model model){
        Optional<Retake> retake = retakeService.getRetake(retakeId);
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        model.addAttribute("teachers",teachers);
        if(retake.isPresent()){
            model.addAttribute("student",retake.get());
            List<Gruppa> allGruppa = gruppaService.getAllGruppa();
            model.addAttribute("allGruppa",allGruppa);

            return "updateRetake";
        }else {
            return "404";
        }
    }

    @GetMapping("/retake/student/delete/{id}")
    public String deleteRetakeStudent(@PathVariable("id") Long studentId,Model model){
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        model.addAttribute("teachers",teachers);
        retakeService.deleteRetaeke(studentId);
        return "redirect:/retake";
    }

    @PostMapping("/updateRetake")
    public String updateStudentExamInfo(@ModelAttribute("retake") Retake retake
    ){
        retakeService.updateRetake(retake);
        return "redirect:/retake";
    }

    @PostMapping("/searchretake")
    public String searchRetake(@RequestParam("studentName") String studentName,Model model){
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        List<Gruppa> allGruppa = gruppaService.getAllGruppa();
        model.addAttribute("allGruppa",allGruppa);


        if(studentName!=null){
            List<Retake> list = retakeService.searchRetakeResultByStudentName(studentName);
            model.addAttribute("teachers",teachers);
            model.addAttribute("title","Пересдача");
            model.addAttribute("exam",new Exam());
            model.addAttribute("examResults",list);
            model.addAttribute("studentName",studentName);
        }else {
            List<Retake> allRetake = retakeService.getAllRetake();
            model.addAttribute("teachers",teachers);
            model.addAttribute("title","Пересдача");
            model.addAttribute("exam",new Exam());
            model.addAttribute("examResults",allRetake);
        }

        return "retake";
    }

    @PostMapping("/searchlevelbyretake")
    public String searchRetakeByLevel(@RequestParam("level") String level,Model model){
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        List<Gruppa> allGruppa = gruppaService.getAllGruppa();
        model.addAttribute("allGruppa",allGruppa);
        if(level!=null){
            List<Retake> list = retakeService.searchRetakeResultByLevel(level);

            model.addAttribute("teachers",teachers);
            model.addAttribute("title","Пересдача");
            model.addAttribute("exam",new Exam());
            model.addAttribute("examResults",list);
        }else {
            List<Retake> allRetake = retakeService.getAllRetake();
            model.addAttribute("teachers",teachers);
            model.addAttribute("title","Пересдача");
            model.addAttribute("exam",new Exam());
            model.addAttribute("examResults",allRetake);
        }

        return "retake";
    }

}
