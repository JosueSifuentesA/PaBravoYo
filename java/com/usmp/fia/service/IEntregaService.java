package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import com.usmp.fia.entity.TipoEntrega;

public interface IEntregaService {

	
	List<TipoEntrega>findAll();
	
	Optional<TipoEntrega>findById(Integer id);

	TipoEntrega save(TipoEntrega tipoEntrega);
	
	
	public void delete(Integer id);
}
