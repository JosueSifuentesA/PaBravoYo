package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmp.fia.dao.CategoriaDao;
import com.usmp.fia.entity.CategoriaPlato;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	@Autowired
	CategoriaDao categoriaDao;
	
	
	@Override
	public List<CategoriaPlato> findAll() {
		
		return (List<CategoriaPlato>) categoriaDao.findAll();
	}


	@Override
	public String generarCodigo() {
		
		String codigo = "-2022";
		
		int numAleatorio = (int) (Math.random()*(9999-0)+0);
		
		int tamaño = 9;
		
		codigo = numAleatorio + codigo;
		
		for(int i = 0 ; i < tamaño-codigo.length() ; i++) {
			
			codigo = "0" + codigo;
			
		}	
		return codigo;
	}

	@Override
	public Optional<CategoriaPlato> findById(Integer codcategoria) {

		return categoriaDao.findById(codcategoria);
	}


	@Override
	public CategoriaPlato save(CategoriaPlato categoria) {
	
		return categoriaDao.save(categoria);
	}

	@Override
	public void delete(Integer id) {
		categoriaDao.deleteById(id);
		
	}

}
