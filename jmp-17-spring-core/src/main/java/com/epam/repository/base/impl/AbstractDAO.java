package com.epam.repository.base.impl;

import com.epam.model.User;
import com.epam.repository.base.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDAO<E, K extends Serializable> implements GenericDAO<E, K> {
    private final Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public E create(E entity) {
        getSession().persist(entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    public E read(K key) {
        return (E) getSession().get(entityClass, key);
    }

    @Transactional
    public E update(E entity) {
        getSession().update(entity);
        return entity;
    }

    @Transactional
    public void delete(E entity) {
        getSession().delete(entity);
    }

    public List<E> getAll() {
        return getSession().createCriteria(User.class).list();
    }
}
