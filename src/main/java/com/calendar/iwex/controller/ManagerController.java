package com.calendar.iwex.controller;

import com.calendar.iwex.entity.*;
import com.calendar.iwex.service.ExamService;
import com.calendar.iwex.service.GruppaService;
import com.calendar.iwex.service.ManagerService;
import com.calendar.iwex.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ManagerService managerService;
    @Autowired
    private ExamService examService;
    @Autowired
    private GruppaService gruppaService;
    @GetMapping("/manager")
    public String getManagerPage(Model model){
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        List<Manager> managers = managerService.getAllStudent();
        model.addAttribute("teachers",teachers);
        model.addAttribute("title","менеджер");
        model.addAttribute("examResults",managers);

        List<Gruppa> allGruppa = gruppaService.getAllGruppa();
        model.addAttribute("allGruppa",allGruppa);
        model.addAttribute("totalStudent",managers.size());

        return "manager";
    }


    @PostMapping("/searchmanager")
    public String searchRetake(@RequestParam("studentName") String studentName, Model model){
        List<TeacherModel> teachers = teacherService.getAllTeacher();

        if(studentName!=null){
            List<Manager> managers = managerService.searchManagerinfoByStudentName(studentName);
            model.addAttribute("teachers",teachers);
            model.addAttribute("title","менеджер");
            model.addAttribute("examResults",managers);

            List<Gruppa> allGruppa = gruppaService.getAllGruppa();
            model.addAttribute("allGruppa",allGruppa);
            model.addAttribute("totalStudent",managers.size());

        }else {
            List<Manager> managers = managerService.getAllStudent();
            model.addAttribute("teachers",teachers);
            model.addAttribute("title","менеджер");
            model.addAttribute("examResults",managers);

            List<Gruppa> allGruppa = gruppaService.getAllGruppa();
            model.addAttribute("allGruppa",allGruppa);
            model.addAttribute("totalStudent",managers.size());

        }

        return "manager";
    }

    @PostMapping("/searchlevel")
    public String searchLevel(@RequestParam("level") String level,Model model){
        List<TeacherModel> teachers = teacherService.getAllTeacher();


        if(level!=null){
            List<Manager> managerList = managerService.searchManagerInfooByLevel(level);
            model.addAttribute("teachers",teachers);
            model.addAttribute("title","менеджер");
            model.addAttribute("examResults",managerList);
            model.addAttribute("totalStudent",managerList.size());

            List<Gruppa> allGruppa = gruppaService.getAllGruppa();
            model.addAttribute("allGruppa",allGruppa);
        }else {
            List<Manager> managers = managerService.getAllStudent();
            model.addAttribute("teachers",teachers);
            model.addAttribute("title","менеджер");
            model.addAttribute("examResults",managers);
            model.addAttribute("totalStudent",managers.size());

            List<Gruppa> allGruppa = gruppaService.getAllGruppa();
            model.addAttribute("allGruppa",allGruppa);
        }

        return "manager";
    }
}
