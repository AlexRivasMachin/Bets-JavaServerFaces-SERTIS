package dev.sertis.betsjsf.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long eventId;
    private String eventDescription;
    private LocalDate eventDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questionsForThisEvent;

    public Event() {
    }

    public Event(String eventDescription, LocalDate eventDate) {
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public List<Question> getQuestionsForThisEvent() {
        return questionsForThisEvent;
    }

    public void setQuestionsForThisEvent(List<Question> questionsForThisEvent) {
        this.questionsForThisEvent = questionsForThisEvent;
    }

    @Override
    public String toString(){
        return eventId +";"+ eventDescription;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + eventId);
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        return Objects.equals(eventId, other.eventId);
    }

}
