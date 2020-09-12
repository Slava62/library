package com.example.library.service;

import java.util.List;

public interface CRUDService<T> {
    T create(T obj);

    T update(T obj, Long id);

    T getById(Long id);

    List<T> getAll();

    void delete(Long id);
}
