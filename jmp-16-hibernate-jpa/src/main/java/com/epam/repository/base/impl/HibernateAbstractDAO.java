package com.epam.repository.base.impl;

import com.epam.repository.base.GenericDAO;
import com.epam.repository.factory.HibernateFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.Consumer;

public class HibernateAbstractDAO<E, K extends Serializable> implements GenericDAO<E, K> {

    protected Class<E> entityClass;
    protected SessionFactory factory;

    public HibernateAbstractDAO() {
        this.factory = HibernateFactory.getSessionFactory();
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    private void decorator(Consumer<E> func, Session session, E entity) {
        Transaction transaction = session.beginTransaction();
        func.accept(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public E create(E entity) {
        Session session = factory.openSession();
        decorator(session::save, session, entity);
        return entity;
    }

    @Override
    public E read(K key) {
        Session session = factory.openSession();
        E entity = session.get(entityClass, key);
        session.close();
        return entity;
    }

    @Override
    public E update(E entity) {
        Session session = factory.openSession();
        decorator(session::update, session, entity);
        return entity;
    }

    @Override
    public void delete(E entity) {
        Session session = factory.openSession();
        decorator(session::delete, session, entity);
    }

    @Override
    public List<E> getAll() {
        Session session = factory.openSession();
        List<E> entities = session.createCriteria(entityClass).list();
        session.close();
        return entities;
    }
}
