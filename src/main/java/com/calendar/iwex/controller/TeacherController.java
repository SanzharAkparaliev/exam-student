package com.calendar.iwex.controller;

import com.calendar.iwex.entity.Teacher;
import com.calendar.iwex.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
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
        List<Teacher> allTeacher = teacherService.getAllTeacher();
        model.addAttribute("title","Teachers");
        model.addAttribute("teachers",allTeacher);
        return "teachers";
    }

    @GetMapping("/teacher/edit/{id}")
    public String getTeacher(Model model,@PathVariable("id") Long id){
        List<Teacher> allTeacher = teacherService.getAllTeacher();
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
