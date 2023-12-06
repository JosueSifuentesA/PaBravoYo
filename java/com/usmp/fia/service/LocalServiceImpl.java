package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmp.fia.dao.LocalDao;
import com.usmp.fia.entity.TipoEntrega;
import com.usmp.fia.entity.TipoLocal;

@Service
public class LocalServiceImpl implements ILocalService{

	@Autowired
	private LocalDao localDao;

	@Override
	public List<TipoLocal> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoLocal>) localDao.findAll();
	}

	@Override
	public Optional<TipoLocal> findById(Integer id) {
		// TODO Auto-generated method stub
		return localDao.findById(id);
	}

	@Override
	public TipoLocal save(TipoLocal tipoLocal) {
		// TODO Auto-generated method stub
		return localDao.save(tipoLocal);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		localDao.deleteById(id);
	}


}
