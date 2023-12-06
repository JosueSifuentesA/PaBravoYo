package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import com.usmp.fia.entity.TipoPago;

public interface IPagoService {

	List<TipoPago>findAll();
	Optional<TipoPago>findById(Integer id);
	TipoPago save(TipoPago tipoPago);
	public void delete(Integer id);
}
