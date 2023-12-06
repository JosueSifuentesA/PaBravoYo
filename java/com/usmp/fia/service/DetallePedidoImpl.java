package com.usmp.fia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmp.fia.dao.DetallePedidoDao;
import com.usmp.fia.entity.DetallePedido;

@Service
public class DetallePedidoImpl implements IDetallePedidoService {

	@Autowired
	private DetallePedidoDao detallePedidoDao;

	@Override
	public DetallePedido save(DetallePedido detallePedido) {
		return detallePedidoDao.save(detallePedido);
	}

}
