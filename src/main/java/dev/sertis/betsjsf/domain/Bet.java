package dev.sertis.betsjsf.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long betId;
    @OneToOne(cascade = {CascadeType.ALL}, targetEntity = User.class)
    private User userWhoPlacedBet;
    private float amountPlacedOnBet;
    @OneToOne(cascade = {CascadeType.ALL}, targetEntity = Forecast.class)
    private Forecast associatedForecast;

    public Bet() {
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public User getUserWhoPlacedBet() {
        return userWhoPlacedBet;
    }

    public void setUserWhoPlacedBet(User userWhoPlacedBet) {
        this.userWhoPlacedBet = userWhoPlacedBet;
    }

    public float getAmountPlacedOnBet() {
        return amountPlacedOnBet;
    }

    public void setAmountPlacedOnBet(float amountPlacedOnBet) {
        this.amountPlacedOnBet = amountPlacedOnBet;
    }

    public Forecast getAssociatedForecast() {
        return associatedForecast;
    }

    public void setAssociatedForecast(Forecast associatedForecast) {
        this.associatedForecast = associatedForecast;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bet other = (Bet) obj;
        return Objects.equals(this.betId, other.betId);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
