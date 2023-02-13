package com.calendar.iwex.controller;

import com.calendar.iwex.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
