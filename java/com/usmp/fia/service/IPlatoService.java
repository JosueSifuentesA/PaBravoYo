package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import com.usmp.fia.entity.Plato;

public interface IPlatoService {

	public List<Plato> findAll();

	public Plato save(Plato plato);

	public Optional<Plato> get(Integer id);

	public void update(Plato plato);

	public void delete(Integer id);

	public Plato findOne(Integer id);
}
