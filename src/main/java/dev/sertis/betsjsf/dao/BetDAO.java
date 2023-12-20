package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.domain.Bet;

import java.util.List;

public interface BetDAO {

    List<Bet> getBetsByForecastId(Long forecastID);
    void save(Bet bet);

    Bet update(Bet bet);

    void delete(Bet bet);

    Bet getBetById(Long id);

}
