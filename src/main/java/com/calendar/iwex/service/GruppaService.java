package com.calendar.iwex.service;

import com.calendar.iwex.entity.Archive;
import com.calendar.iwex.entity.Exam;
import com.calendar.iwex.entity.Gruppa;
import com.calendar.iwex.entity.Teacher;
import com.calendar.iwex.repository.ArchiveRepository;
import com.calendar.iwex.repository.GruppaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;

@Service
public class GruppaService {
    @Autowired
    private GruppaRepository gruppaRepository;
    @Autowired
    private ArchiveRepository archiveRepository;


    public void sendToArchive(Long gruppaId, LocalDate archiveDate) {
        Gruppa gruppa = gruppaRepository.findById(gruppaId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid gruppa id: " + gruppaId));

        if (gruppa.getArchive() != null) {
            throw new IllegalStateException("Gruppa is already in the archive");
        }

        Archive archive = new Archive(gruppa.getName(), archiveDate);
        archive.getGroups().add(gruppa);

        gruppa.setArchive(archive);
        gruppa.setExams(Collections.emptySet());

        archiveRepository.save(archive);
        gruppaRepository.save(gruppa);
    }

    public void createGruppa(Gruppa gruppa){
        gruppaRepository.save(gruppa);
    }

    public List<Gruppa> getGruppaByTeacher(Teacher teacher){
        Archive archive = null;
        return gruppaRepository.findByTeacherAndArchive(teacher,archive);
    }

    public List<Gruppa> getAllGruppa(){
        Archive archive = null;

        return gruppaRepository.findByArchive(archive);
    }

    public Optional<Gruppa> getGruppaById(Long id){
        return gruppaRepository.findById(id);
    }

    public void deleteGroup(Long id){
        gruppaRepository.deleteById(id);
    }

    public void updateGroup(Long groupId, String groupName) {
        Optional<Gruppa> byId = gruppaRepository.findById(groupId);
        byId.get().setName(groupName);
        gruppaRepository.save(byId.get());
    }
}
