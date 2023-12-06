package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmp.fia.dao.PagoDao;
import com.usmp.fia.entity.TipoPago;

@Service
public class PagoServiceImpl implements IPagoService{

	@Autowired
	PagoDao pagoDao;
	
	@Override
	public List<TipoPago> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoPago>) pagoDao.findAll();
	}

	@Override
	public Optional<TipoPago> findById(Integer id) {
		// TODO Auto-generated method stub
		return pagoDao.findById(id);
	}

	@Override
	public TipoPago save(TipoPago tipoPago) {
		// TODO Auto-generated method stub
		return pagoDao.save(tipoPago);
	}

	@Override
	public void delete(Integer id) {
		pagoDao.deleteById(id);
	}

}
