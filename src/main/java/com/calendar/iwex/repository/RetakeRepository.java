package com.calendar.iwex.repository;

import com.calendar.iwex.entity.Retake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetakeRepository extends JpaRepository<Retake,Long> {
    public Retake findByAnn(String ann);

    @Query(value = "select * from retake r where r.student_name like %:keyword% ", nativeQuery = true)
    public List<Retake> findByKeyWord(@Param("keyword") String keyword);

    public List<Retake> findByLevel(String level);

}
