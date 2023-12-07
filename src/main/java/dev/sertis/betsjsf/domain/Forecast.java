package dev.sertis.betsjsf.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Forecast {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer forecastId;
    private String forecastDescription;
    private float potentialGain;
    @ManyToOne(targetEntity = Question.class, cascade = CascadeType.ALL)
    private Question associatedQuestion;
    @OneToMany( targetEntity = Bet.class)
    private Set<Bet> betsForThisForecast;

    public Forecast() {
    }

    public Integer getForecastId() {
        return forecastId;
    }

    public void setForecastId(Integer forecastId) {
        this.forecastId = forecastId;
    }

    public String getForecastDescription() {
        return forecastDescription;
    }

    public void setForecastDescription(String forecastDescription) {
        this.forecastDescription = forecastDescription;
    }

    public float getPotentialGain() {
        return potentialGain;
    }

    public void setPotentialGain(float potentialGain) {
        this.potentialGain = potentialGain;
    }

    public Question getAssociatedQuestion() {
        return associatedQuestion;
    }

    public void setAssociatedQuestion(Question associatedQuestion) {
        this.associatedQuestion = associatedQuestion;
    }

    public Set<Bet> getBetsForThisForecast() {
        return betsForThisForecast;
    }

    public void setBetsForThisForecast(Set<Bet> betsForThisForecast) {
        this.betsForThisForecast = betsForThisForecast;
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
