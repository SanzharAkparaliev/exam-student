package com.calendar.iwex.repository;

import com.calendar.iwex.entity.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive,Long> {
}
