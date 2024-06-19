package com.peretto.sgPeretto.service;

import java.util.List;

// Interface que sirve para todas las entidades
// Se usa "E" porque es para cualquier entidad - funciona como genérico
// Se usa "D" porque es para cualquier dto

public interface BaseService<E, ID> {
    List<E> findAll() throws Exception;

    E findById(Long id) throws Exception;

    E save (E entity) throws Exception;

    E update (ID id, E entity) throws Exception;

    boolean delete (Long id) throws Exception;
}
