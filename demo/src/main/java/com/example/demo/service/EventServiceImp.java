package com.example.demo.service;

import com.example.demo.DTO.EventDTO;
import com.example.demo.ResponseForEvent;
import com.example.demo.converter.EventConverter;
import com.example.demo.dataAccess.Event;
import com.example.demo.dataAccess.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class EventServiceImp implements EventService {

    @Autowired
    EventRepository eventRepository;


    @Override
    public List<EventDTO> getEventsByUserId(long id){
        return EventConverter.EventListConvert(eventRepository.findByUser(id));
    }

    @Override
    public long addEvent(Event event){
        Event newEvent = new Event();
        newEvent.setType(event.getType());
        newEvent.setUser(event.getUser());
        newEvent.setDescription(event.getDescription());
        newEvent.setTitle(event.getTitle());
        newEvent.setDate(event.getDate());
        newEvent.setUid(event.getUid());
        Event savedEvent = eventRepository.save(newEvent);
        return savedEvent.getId();
    }

    @Override
    public List<LocalDate> findDateByUserId(long UserId){
        return eventRepository.findDateByUser(UserId);
    }
    @Override
    public void deleteEventByUid(Long Uid){
        eventRepository.deleteByUid(Uid);
    }

    @Override
    public List<ResponseForEvent<List<EventDTO>>> getFormatEventsByUserId(long UserId){
        List<ResponseForEvent<List<EventDTO>>> response = new ArrayList<>();
        List<LocalDate> dates = findDateByUserId(UserId);
        for(LocalDate date : dates){
            List<EventDTO> eventDTOList =EventConverter.EventListConvert
                    (eventRepository.findByDateAndUser(date,UserId));
            ResponseForEvent<List<EventDTO>> responseForEvent = ResponseForEvent.newSuccess(eventDTOList,date);
            response.add(responseForEvent);
        }
        return response;
    }
}
