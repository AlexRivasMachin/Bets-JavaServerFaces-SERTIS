package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.domain.User;

public interface UserDAO{

    User findByDNI(String dni);

    void save(User user);

    void update(User user);

    void delete(User user);
}
