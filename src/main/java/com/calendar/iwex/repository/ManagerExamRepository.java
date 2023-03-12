package com.calendar.iwex.repository;

import com.calendar.iwex.entity.ManagerExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerExamRepository extends JpaRepository<ManagerExam,Long> {
}
