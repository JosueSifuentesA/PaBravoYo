package com.usmp.fia.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usmp.fia.entity.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer>{
	Optional<Usuario>findByCodigo(String codigo);
}
