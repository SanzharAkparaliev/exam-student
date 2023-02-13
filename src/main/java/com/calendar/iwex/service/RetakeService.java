package com.calendar.iwex.service;

import com.calendar.iwex.entity.Exam;
import com.calendar.iwex.entity.Retake;
import com.calendar.iwex.repository.ExamRepository;
import com.calendar.iwex.repository.RetakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RetakeService {
    @Autowired
    private RetakeRepository retakeRepository;
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamService examService;

    public void createRateke(Exam exam){
        Retake byAnnRetake = retakeRepository.findByAnn(exam.getAnn());
            if (byAnnRetake != null){
                LocalDate Date = LocalDate.parse(exam.getDate());
                byAnnRetake.setStudentName(exam.getStudentName());
                byAnnRetake.setTime(exam.getTime());
                byAnnRetake.setSpeaking(exam.getSpeaking());
                byAnnRetake.setWriting(exam.getWriting());
                byAnnRetake.setTotal(exam.getTotal());
                byAnnRetake.setCount(byAnnRetake.getCount() + 1);
                byAnnRetake.setDate(String.valueOf(Date.plusDays(7)));
                byAnnRetake.setAnn(exam.getAnn());
                byAnnRetake.setLevel(exam.getLevel());
                byAnnRetake.setComment(exam.getComment());
                byAnnRetake.setResult(exam.getResult());
                retakeRepository.save(byAnnRetake);
            }else {
                LocalDate Date = LocalDate.parse(exam.getDate());
                Retake retake = new Retake();
                retake.setStudentName(exam.getStudentName());
                retake.setTime(exam.getTime());
                retake.setDate(String.valueOf(Date.plusDays(7)));
                retake.setAnn(exam.getAnn());
                retake.setTotal(exam.getTotal());
                retake.setSpeaking(exam.getSpeaking());
                retake.setWriting(exam.getWriting());
                retake.setComment(exam.getComment());
                retake.setCount(0);
                retake.setLevel(exam.getLevel());
                retake.setResult(exam.getResult());
                retakeRepository.save(retake);
            }

    }

    public List<Retake> getAllRetake(){
        return retakeRepository.findAll();
    }

    public void deleteRetaeke(Long studentId){
        Optional<Retake> byIdRetake = retakeRepository.findById(studentId);
        retakeRepository.delete(byIdRetake.get());
    }

    public Optional<Retake> getRetake(Long id){
        return retakeRepository.findById(id);
    }

    public void updateRetake(Retake newRetake) {
        Retake retake = retakeRepository.findById(newRetake.getId()).get();
        retake.setComment(newRetake.getComment());
        retake.setResult(newRetake.getResult());
        retake.setWriting(newRetake.getWriting());
        retake.setTotal(newRetake.getTotal());
        retake.setTime(newRetake.getTime());
        retake.setDate(newRetake.getDate());
        retake.setSpeaking(newRetake.getSpeaking());
        retake.setLevel(newRetake.getLevel());
        retake.setTotal(newRetake.getSpeaking() + newRetake.getWriting());

        if(!newRetake.getResult().equals("сдал(а)")){
            try {
                examService.updateExamRetake(retake);
                retakeRepository.deleteById(retake.getId());
            }catch (Exception e){

            }

        }else {
            retakeRepository.save(retake);
        }
    }

    public List<Retake> searchRetakeResultByStudentName(String studentName){
        return retakeRepository.findByKeyWord(studentName);
    }

    public List<Retake> searchRetakeResultByLevel(String level){
        return retakeRepository.findByLevel(level);
    }
}
