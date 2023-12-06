package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmp.fia.dao.PlatoDao;
import com.usmp.fia.entity.Plato;
@Service
public class PlatoServiceImpl implements IPlatoService{
	@Autowired
	private PlatoDao platoDao;

	@Override
	public List<Plato> findAll() {

		return platoDao.findAll();
	}

	@Override
	public Plato save(Plato plato) {

		return platoDao.save(plato);
	}

	@Override
	public Optional<Plato> get(Integer id) {

		return platoDao.findById(id);
	}

	@Override
	public void update(Plato plato) {

		platoDao.save(plato);

	}

	@Override
	public void delete(Integer id) {

		platoDao.deleteById(id);

	}

	@Override
	public Plato findOne(Integer id) {

		return platoDao.findById(id).orElse(null);
	}
}
