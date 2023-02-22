package com.calendar.iwex.controller;

import com.calendar.iwex.entity.Teacher;
import com.calendar.iwex.entity.TeacherModel;
import com.calendar.iwex.service.GruppaService;
import com.calendar.iwex.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private GruppaService gruppaService;
//
//    @GetMapping("/teacher/{id}")
//    public String getGruppaByTeacher(@PathVariable("id") Long id,Model model){
//        model.addAttribute("title","IWEX");
//        Optional<Teacher> teacher = teacherService.getTeacher(id);
//        gruppaService.getGruppaByTeacher(teacher.get());
//    }
    @PostMapping("/saveTeacher")
    public String saveTeacher(@RequestParam("teacher") String teacher){
        teacherService.createTeacher(teacher);
        return "redirect:/";
    }

    @GetMapping("/teacher/delete/{id}")
    public String removeTeacher(@PathVariable("id") Long id){
        teacherService.deleteTeacher(id);
        return "redirect:/";

    }

    @GetMapping("/edit/teacher")
    public String getTeachersPage(Model model){
        List<TeacherModel> allTeacher = teacherService.getAllTeacher();
        model.addAttribute("title","Teachers");
        model.addAttribute("teachers",allTeacher);

        return "teachers";
    }

    @GetMapping("/teacher/edit/{id}")
    public String getTeacher(Model model,@PathVariable("id") Long id){
        List<TeacherModel> allTeacher = teacherService.getAllTeacher();
        Teacher teacher = teacherService.getTeacher(id).get();
        model.addAttribute("title","Teachers");
        model.addAttribute("teachers",allTeacher);
        model.addAttribute("teacher",teacher);

        return "teacherEdit";
    }

    @PostMapping("/updateTeacher")
    public String updateTeacher(@ModelAttribute("teacher") Teacher teacher){
        teacherService.updateTeacher(teacher);
        return "redirect:/";
    }
}
