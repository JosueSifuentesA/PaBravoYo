package com.usmp.fia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmp.fia.dao.PedidoDao;
import com.usmp.fia.entity.Pedido;
import com.usmp.fia.entity.Usuario;
@Service
public class PedidoServiceImpl implements IPedidoService{

	@Autowired
	private PedidoDao pedidoDao;
	
	@Override
	public List<Pedido> findAll() {
		return pedidoDao.findAll();
	}

	@Override
	public Optional<Pedido> findById(Integer id) {
		return pedidoDao.findById(id);
	}

	@Override
	public Pedido save(Pedido pedido) {
		return pedidoDao.save(pedido);
	}

	@Override
	public String generarNumeroPedido() {
		/*int numero = 0;
		String numeroConcatenado = "";

		List<Pedido> pedidos = findAll();

		List<Integer> numeros = new ArrayList<Integer>();

		pedidos.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

		if (pedidos.isEmpty()) {
			numero = 1;
		} else {
			numero = numeros.stream().max(Integer::compare).get();
			numero++;
		}

		if (numero < 10) {
			numeroConcatenado = "0000000" + String.valueOf(numero);
		} else if (numero < 100) {
			numeroConcatenado = "000000" + String.valueOf(numero);
		} else if (numero < 1000) {
			numeroConcatenado = "00000" + String.valueOf(numero);
		}*/
		return "";
	}

	@Override
	public List<Pedido> findByUsuario(Usuario usuario) {
		return pedidoDao.findByUsuario(usuario);
	}

	@Override
	public Pedido findOne(Integer id) {
		return pedidoDao.findById(id).orElse(null);
	}
}
