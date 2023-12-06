package com.usmp.fia.dao;

import org.springframework.data.repository.CrudRepository;

import com.usmp.fia.entity.TipoEntrega;

public interface EntregaDao extends CrudRepository<TipoEntrega, Integer> {

}
