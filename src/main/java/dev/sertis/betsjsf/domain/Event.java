package dev.sertis.betsjsf.domain;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Event  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long eventId;
    private String eventDescription;
    private Date eventDate;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Question> questionsForThisEvent;

    public Event() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Set<Question> getQuestionsForThisEvent() {
        return questionsForThisEvent;
    }

    public void setQuestionsForThisEvent(Set<Question> questionsForThisEvent) {
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
