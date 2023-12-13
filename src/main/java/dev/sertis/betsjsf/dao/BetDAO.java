package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.domain.Bet;

public interface BetDAO {

    void save(Bet bet);

    void update(Bet bet);

    void delete(Bet bet);

    Bet getBetById(Integer id);

}
