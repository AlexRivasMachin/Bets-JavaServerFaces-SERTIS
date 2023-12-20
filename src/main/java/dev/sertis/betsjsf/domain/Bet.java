package dev.sertis.betsjsf.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Bet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long betId;
    @ManyToOne(fetch = FetchType.EAGER)
    private User userWhoPlacedBet;
    private double amountPlacedOnBet;
    @OneToOne(cascade = {CascadeType.ALL})
    private Forecast associatedForecast;

    public Bet() {
    }

    public Bet(User userWhoPlacedBet, double amountPlacedOnBet, Forecast associatedForecast) {
        this.userWhoPlacedBet = userWhoPlacedBet;
        this.amountPlacedOnBet = amountPlacedOnBet;
        this.associatedForecast = associatedForecast;
    }

    public long getBetId() {
        return betId;
    }

    public void setBetId(long betId) {
        this.betId = betId;
    }

    public User getUserWhoPlacedBet() {
        return userWhoPlacedBet;
    }

    public void setUserWhoPlacedBet(User userWhoPlacedBet) {
        this.userWhoPlacedBet = userWhoPlacedBet;
    }

    public double getAmountPlacedOnBet() {
        return amountPlacedOnBet;
    }

    public void setAmountPlacedOnBet(double amountPlacedOnBet) {
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
