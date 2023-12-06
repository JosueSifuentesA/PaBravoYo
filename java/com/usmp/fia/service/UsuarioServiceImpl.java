package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmp.fia.dao.UsuarioDao;
import com.usmp.fia.entity.Usuario;
@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	public Optional<Usuario> findById(Integer id) {
		return usuarioDao.findById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public Optional<Usuario> findByCodigo(String codigo) {
		return usuarioDao.findByCodigo(codigo);
	}

	@Override
	public Usuario findOne(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

}
