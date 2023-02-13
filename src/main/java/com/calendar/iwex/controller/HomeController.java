package com.calendar.iwex.controller;

import com.calendar.iwex.entity.Teacher;
import com.calendar.iwex.service.DayService;
import com.calendar.iwex.service.EventService;
import com.calendar.iwex.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private EventService eventService;
    @Autowired
    private DayService dayService;

    @Autowired
    private TeacherService teacherService;




    @GetMapping("/")
    public String getHomePage(Model model) throws SQLException, ClassNotFoundException {
        List<Teacher> teachers = teacherService.getAllTeacher();
        model.addAttribute("teachers",teachers);
        model.addAttribute("title","IWEX");
        return "index";
    }

    @PostMapping("/saveEvent")
    public String saveEvent(@RequestParam("teacherName") String teacherName,
                            @RequestParam("clock") String clock,
                            @RequestParam("date") String date,
                            @RequestParam("group") String group
    ){

      eventService.saveEvent(date,clock,teacherName,group);
        return "redirect:/calendar";
    }


}
