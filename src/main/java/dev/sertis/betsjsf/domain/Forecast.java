package dev.sertis.betsjsf.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Forecast implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long forecastId;
    private String forecastDescription;
    private double potentialGain;
    @ManyToOne(targetEntity = Question.class, cascade = CascadeType.ALL)
    private Question associatedQuestion;

    public Forecast() {
    }

    public Forecast(String forecastDescription, double potentialGain, Question associatedQuestion) {
        this.forecastDescription = forecastDescription;
        this.potentialGain = potentialGain;
        this.associatedQuestion = associatedQuestion;
    }

    public long getForecastId() {
        return forecastId;
    }

    public void setForecastId(long forecastId) {
        this.forecastId = forecastId;
    }

    public String getForecastDescription() {
        return forecastDescription;
    }

    public void setForecastDescription(String forecastDescription) {
        this.forecastDescription = forecastDescription;
    }

    public double getPotentialGain() {
        return potentialGain;
    }

    public void setPotentialGain(double potentialGain) {
        this.potentialGain = potentialGain;
    }

    public Question getAssociatedQuestion() {
        return associatedQuestion;
    }

    public void setAssociatedQuestion(Question associatedQuestion) {
        this.associatedQuestion = associatedQuestion;
    }

    @Override
    public String toString() {
        return forecastId +";"+ forecastDescription +";"+ potentialGain;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Forecast other = (Forecast) obj;
        return Objects.equals(this.forecastId, other.forecastId);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
