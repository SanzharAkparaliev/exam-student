package com.calendar.iwex.controller;

import com.calendar.iwex.entity.Exam;
import com.calendar.iwex.entity.Gruppa;
import com.calendar.iwex.entity.Teacher;
import com.calendar.iwex.entity.TeacherModel;
import com.calendar.iwex.service.ExamService;
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
public class GroupController {


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GruppaService gruppaService;
    @Autowired
    private ExamService examService;

    @GetMapping("/group/{id}")
    public String getGroupPage(Model model, @PathVariable("id") Long teacherId){
        model.addAttribute("title","Группа");
        model.addAttribute("teacherId",teacherId);

        List<TeacherModel> teachers = teacherService.getAllTeacher();
        List<Gruppa> allGruppa = gruppaService.getAllGruppa();
        model.addAttribute("teachers",teachers);
        model.addAttribute("allGruppa",allGruppa);

        return "group";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@RequestParam("gruppa") String groupName,
                            @RequestParam("teacher_id") Long teacherId){
        Gruppa gruppa = new Gruppa();
        Optional<Teacher> teacher = teacherService.getTeacher(teacherId);
        gruppa.setTeacher(teacher.get());
        gruppa.setName(groupName);
        System.out.println("Group " + groupName);

        gruppaService.createGruppa(gruppa);
        return "redirect:/";
    }

    @GetMapping("/group/get/{id}")
    public String getExamByGruppa(@PathVariable("id") Long id,Model model){
        Optional<Gruppa> gruppaById = gruppaService.getGruppaById(id);
        List<Exam> examByGroup = examService.getExamByGroup(gruppaById.get());
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        List<Gruppa> allGruppa = gruppaService.getAllGruppa();

        model.addAttribute("title",gruppaById.get().getName());
        model.addAttribute("examResults",examByGroup);
        model.addAttribute("groupId",gruppaById.get().getId());
        model.addAttribute("teachers",teachers);
        model.addAttribute("allGruppa",allGruppa);

        return "exam";

    }

    @GetMapping("teacher/groups/{tId}")
    public String getGropsPage(Model model,@PathVariable("tId") Long teacherId){
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        List<Gruppa> allGruppa = gruppaService.getAllGruppa();
        Optional<Teacher> teacher = teacherService.getTeacher(teacherId);
        List<Gruppa> gruppaByTeacher = gruppaService.getGruppaByTeacher(teacher.get());
        model.addAttribute("gruppaByTeacher",gruppaByTeacher);
        model.addAttribute("teachers",teachers);
        model.addAttribute("title",teacher.get().getName() );
        model.addAttribute("allGruppa",allGruppa);

        return "groups";
    }

    @GetMapping("/gruppa/delete/{id}/teacher/{tId}")
    public String deleteGroup(@PathVariable("id")Long groupId,@PathVariable("tId") Long teacherId){
        gruppaService.deleteGroup(groupId);
        return "redirect:/teacher/groups/" + teacherId;
    }

    @GetMapping("/gruppa/edit/{id}/teacher/{tId}")
    public String getEditGroupPage(Model model,@PathVariable("id")Long groupId,@PathVariable("tId") Long teacherId){
        Optional<Gruppa> gruppaById = gruppaService.getGruppaById(groupId);
        List<TeacherModel> teachers = teacherService.getAllTeacher();
        List<Gruppa> allGruppa = gruppaService.getAllGruppa();
        Optional<Teacher> teacher = teacherService.getTeacher(teacherId);
        model.addAttribute("teachers",teachers);
        model.addAttribute("title",gruppaById.get().getName());
        model.addAttribute("allGruppa",allGruppa);
        model.addAttribute("group",gruppaById);

        return "editGroup";
    }

    @PostMapping("/updateGroup")
    public String  updateGroup(@RequestParam("teacher_id") Long teacherId,
                               @RequestParam("group_id")Long groupId,
                               @RequestParam("gruppa") String groupName){
        gruppaService.updateGroup(groupId,groupName);
        return "redirect:/teacher/groups/"+ teacherId;
    }
}
