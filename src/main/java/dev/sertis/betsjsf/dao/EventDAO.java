package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.domain.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EventDAO {

    Event getEventById(Long eventId);

    List<Event> getEventsByDate(LocalDate date);

    void save(Event event);

    Event update(Event event);

    void delete(Event event);
}
