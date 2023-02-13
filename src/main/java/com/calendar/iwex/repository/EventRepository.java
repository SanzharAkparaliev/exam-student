package com.calendar.iwex.repository;

import com.calendar.iwex.entity.Day;
import com.calendar.iwex.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {


    public Event findByDayAndClock(Day day, String clock);
    Page<Event> findAll(Pageable pageable);
    public void deleteAllByDay(Day day);

}
