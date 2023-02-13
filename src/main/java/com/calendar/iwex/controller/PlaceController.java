package com.calendar.iwex.controller;

import com.calendar.iwex.entity.Day;
import com.calendar.iwex.entity.Event;
import com.calendar.iwex.entity.Teacher;
import com.calendar.iwex.service.DayService;
import com.calendar.iwex.service.EventService;
import com.calendar.iwex.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PlaceController {

    @Autowired
    private DayService dayService;
    @Autowired
    private EventService eventService;
    @Autowired
    private TeacherService teacherService;


    @GetMapping("/calendar")
    public String getCalendarPage(Model model, HttpSession session){
        dayService.createTable();
        return listByPage(model,1,session);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage, HttpSession httpSession){
        httpSession.setAttribute("pageNumber",currentPage);
        Page<Day> dayList = dayService.findAllDays(currentPage);
        LocalDate dt = LocalDate.parse(LocalDate.now().toString());
        Long totalItems = dayList.getTotalElements();
        int totalPages = dayList.getTotalPages();
        List<Teacher> teachers = teacherService.getAllTeacher();
        Page<Event> events = eventService.getAllEvent(currentPage);
        model.addAttribute("events",events);
        model.addAttribute("teachers",teachers);
        model.addAttribute("title","IWEX");
        model.addAttribute("month",dt.getMonth());
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("days",dayList);
        model.addAttribute("title","Расписание");
        return "calendar";
    }

    @GetMapping("/edit/calendar")
    public String getCalendarTable(Model model){
        List<Teacher> teachers = teacherService.getAllTeacher();
        List<Event> allEventList = eventService.findAllEventList();

        model.addAttribute("teachers",teachers);
        model.addAttribute("title","Iwex");
        model.addAttribute("allEvent",allEventList);
        return "calendarTable";
    }

    @GetMapping("/event/delete/{id}")
    public String deletingEvent(@PathVariable("id") Long eventId){
        eventService.deleteEventsById(eventId);
        return "redirect:/edit/calendar";
    }


}
