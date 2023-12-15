package dev.sertis.betsjsf.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long questionId;
    private String questionDescription;
    private float minimumBetAmount;
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Forecast.class)
    private Forecast winningForecast;
    @ManyToOne(targetEntity = Event.class,cascade = CascadeType.ALL)
    private Event associatedEvent;
    @OneToMany( targetEntity = Forecast.class, cascade = CascadeType.ALL)
    private List<Forecast> forecastsForThisQuestion;

    public Question(){

    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
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

    public List<Forecast> getForecastsForThisQuestion() {
        return forecastsForThisQuestion;
    }

    public void setForecastsForThisQuestion(List<Forecast> forecastsForThisQuestion) {
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
