package com.epam.dao;

import com.epam.model.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<User> create(User user) {
        return Optional.ofNullable(persist(user));
    }

    public Optional<User> read(Long id) {
        return Optional.ofNullable(get(id));
    }

    public void update(User user) {
        persist(user);
    }

    public void delete(User user) {
        currentSession().delete(user);
    }

    public List<User> findAll() {
        return currentSession().createCriteria(User.class).list();
    }
}
