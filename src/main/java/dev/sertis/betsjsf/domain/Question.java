package dev.sertis.betsjsf.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long questionId;
    private String questionDescription;
    private float minimumBetAmount;
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Forecast.class)
    private Forecast winningForecast;
    @ManyToOne(targetEntity = Event.class,cascade = CascadeType.ALL)
    private Event associatedEvent;
    @OneToMany( targetEntity = Forecast.class, cascade = CascadeType.ALL)
    private Set<Forecast> forecastsForThisQuestion;

    public Question(){

    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public float getMinimumBetAmount() {
        return minimumBetAmount;
    }

    public void setMinimumBetAmount(float minimumBetAmount) {
        this.minimumBetAmount = minimumBetAmount;
    }

    public Forecast getWinningForecast() {
        return winningForecast;
    }

    public void setWinningForecast(Forecast winningForecast) {
        this.winningForecast = winningForecast;
    }

    public Event getAssociatedEvent() {
        return associatedEvent;
    }

    public void setAssociatedEvent(Event associatedEvent) {
        this.associatedEvent = associatedEvent;
    }

    public Set<Forecast> getForecastsForThisQuestion() {
        return forecastsForThisQuestion;
    }

    public void setForecastsForThisQuestion(Set<Forecast> forecastsForThisQuestion) {
        this.forecastsForThisQuestion = forecastsForThisQuestion;
    }

    @Override
    public String toString(){
        return questionId +";"+ questionDescription +";"+Float.toString(minimumBetAmount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        return Objects.equals(this.questionId, other.questionId);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
