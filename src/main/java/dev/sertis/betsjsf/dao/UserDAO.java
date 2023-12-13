package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.domain.User;
import dev.sertis.betsjsf.domain.Bet;

import java.util.List;
import java.util.Set;

public interface UserDAO{

    User getUserByDNI(String dni);

    List<Bet> getUserPlacedBetsByDNI(String dni);

    void save(User user);

    void update(User user);

    void delete(User user);
}
