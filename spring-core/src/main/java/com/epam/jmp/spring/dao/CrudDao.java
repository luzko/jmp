package com.epam.jmp.spring.dao;

public interface CrudDao<T> {
    T getById(long id);

    T create(T obj);

    T update(T obj);

    boolean deleteById(long id);
}
