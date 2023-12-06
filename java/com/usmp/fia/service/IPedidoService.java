package com.usmp.fia.service;

import java.util.List;
import java.util.Optional;
import com.usmp.fia.entity.Pedido;
import com.usmp.fia.entity.Usuario;

public interface IPedidoService {

	List<Pedido> findAll();

	Optional<Pedido> findById(Integer id);

	Pedido save(Pedido pedido);

	String generarNumeroPedido();
	
	List<Pedido> findByUsuario(Usuario usuario);
	
	public Pedido findOne(Integer id);
}
