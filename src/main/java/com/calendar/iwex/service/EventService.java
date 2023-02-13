package com.calendar.iwex.service;

import com.calendar.iwex.entity.Day;
import com.calendar.iwex.entity.Event;
import com.calendar.iwex.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private DayService dayService;


    static  final  List<String> clocks = new ArrayList<>(){{
        add("9:30");
        add("11:00");
        add("12:30");
        add("14:00");
        add("15:30");
        add("17:00");
        add("18:30");
    }};


    public void saveEvent(String date, String clock, String teacherName,String group){
        try {
            Day day = dayService.getDay(date);

            Event eventModel = eventRepository.findByDayAndClock(day,clock);
            if(eventModel.getTeacherName() == null){
                eventModel.setTeacherName(teacherName);
                eventModel.setGruppa(group);
                eventRepository.save(eventModel);
            }
        }catch (RuntimeException exception){

        }
    }

    public Page<Event> getAllEvent( int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,35);
        return eventRepository.findAll(pageable);
    }

    public void createEventTable(){
        List<Day> allDay = dayService.getAllDay();

        for(Day day :allDay){
            for (String clock :clocks){
                Event event = new Event();
                event.setDay(day);
                event.setClock(clock);
                eventRepository.save(event);
            }
        }
    }

    public void createOneEvent(Day day){
          for(String clock:clocks){
                Event event = new Event();
                event.setDay(day);
                event.setClock(clock);
                eventRepository.save(event);
          }

    }

    public  void deleteEvents(Day day){
        eventRepository.deleteAllByDay(day);
    }

    public List<Event> findAllEventList(){
        return eventRepository.findAll();
    }

    public void deleteEventsById(Long id){
        Optional<Event> event = eventRepository.findById(id);
        event.get().setTeacherName(null);
        event.get().setGruppa(null);
        eventRepository.save(event.get());
    }
}
