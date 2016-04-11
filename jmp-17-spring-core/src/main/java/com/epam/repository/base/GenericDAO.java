package com.epam.repository.base;

import java.util.List;

public interface GenericDAO<E, K> {
    E create(E entity);

    E read(K key);

    E update(E entity);

    void delete(E entity);

    List<E> getAll();
}