package com.calendar.iwex.repository;

import com.calendar.iwex.entity.Manager;
import com.calendar.iwex.entity.Retake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
    @Query(value = "select * from manager m where m.student_name like %:keyword% ", nativeQuery = true)
    public List<Manager> findByKeyWord(@Param("keyword") String keyword);

    public List<Manager> findByLevel(String level);
}
