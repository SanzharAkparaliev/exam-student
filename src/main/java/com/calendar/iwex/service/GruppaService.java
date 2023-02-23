package com.calendar.iwex.service;

import com.calendar.iwex.entity.Gruppa;
import com.calendar.iwex.entity.Teacher;
import com.calendar.iwex.repository.GruppaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GruppaService {
    @Autowired
    private GruppaRepository gruppaRepository;
    public void createGruppa(Gruppa gruppa){
        gruppaRepository.save(gruppa);
    }

    public List<Gruppa> getGruppaByTeacher(Teacher teacher){
        return gruppaRepository.findByTeacher(teacher);
    }

    public List<Gruppa> getAllGruppa(){
        return gruppaRepository.findAll();
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
