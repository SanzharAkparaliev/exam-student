package com.calendar.iwex.controller;

import com.calendar.iwex.entity.Gruppa;
import com.calendar.iwex.entity.TeacherModel;
import com.calendar.iwex.service.GruppaService;
import com.calendar.iwex.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArchiveController {
    @Autowired
    private GruppaService gruppaService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/archive")
    public String getArchivePage(Model model){
        List<Gruppa> allGruppa = gruppaService.getAllGruppa();
        List<Gruppa> Gruppa = gruppaService.findAllGruppaInArchive();
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        model.addAttribute("teachers",teachers);
        model.addAttribute("allGruppa",allGruppa);
        model.addAttribute("title","Архив");
        model.addAttribute("Gruppa",Gruppa);
        return "archive";
    }



    @GetMapping("/group/archive/status/{id}")
    public String changeArchiveStatus(@PathVariable("id") Long id){
        gruppaService.changingStatusArchive(id);
        return "redirect:/archive";
    }
}
