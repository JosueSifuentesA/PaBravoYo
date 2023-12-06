package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmp.fia.dao.EntregaDao;
import com.usmp.fia.entity.TipoEntrega;

@Service
public class EntregaServiceImpl implements IEntregaService  {
	@Autowired
	private EntregaDao entregaDao;

	@Override
	public List<TipoEntrega> findAll() {
		return (List<TipoEntrega>) entregaDao.findAll();
	}

	@Override
	public Optional<TipoEntrega> findById(Integer id) {
		// TODO Auto-generated method stub
		return entregaDao.findById(id);
	}

	@Override
	public TipoEntrega save(TipoEntrega tipoEntrega) {
		// TODO Auto-generated method stub
		return entregaDao.save(tipoEntrega);
	}

	@Override
	public void delete(Integer id) {
	
		entregaDao.deleteById(id);
	}
}


