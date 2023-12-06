package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;

import com.usmp.fia.entity.TipoLocal;

public interface ILocalService {

	List<TipoLocal>findAll();
	Optional<TipoLocal>findById(Integer id);
	TipoLocal save(TipoLocal tipoLocal);
	public void delete(Integer id);
}
