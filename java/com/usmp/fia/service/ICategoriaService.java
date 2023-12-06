package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import com.usmp.fia.entity.CategoriaPlato;

public interface ICategoriaService {
	
	List<CategoriaPlato> findAll();

	Optional<CategoriaPlato> findById(Integer codcategoria);

	CategoriaPlato save(CategoriaPlato categoria);

	String generarCodigo();
	
	public void delete(Integer id); 
}
