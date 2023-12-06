package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import com.usmp.fia.entity.Usuario;

public interface IUsuarioService {
	
	List<Usuario> findAll();

	Optional<Usuario> findById(Integer id);

	Usuario save(Usuario usuario);

	Optional<Usuario> findByCodigo(String codigo);

	public Usuario findOne(Integer id);

}
