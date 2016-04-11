package com.epam.repository.base.impl;

import com.epam.repository.base.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class JPAAbstractDAO<E, K extends Serializable> implements GenericDAO<E, K> {

    protected Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public JPAAbstractDAO() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    @Override
    public E create(E entity) {
        this.entityManager.persist(entity);
        return entity;
    }

    @Override
    public E read(K key) {
        return this.entityManager.find(entityClass, key);
    }

    @Override
    public E update(E entity) {
        return this.entityManager.merge(entity);
    }

    @Override
    public void delete(E entity) {
        entity = this.entityManager.merge(entity);
        this.entityManager.remove(entity);
    }

    @Override
    public List<E> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(entityClass);
        Root<E> rootEntry = cq.from(entityClass);
        return entityManager
                .createQuery(cq.select(rootEntry))
                .getResultList();
    }
}
