package com.usmp.fia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usmp.fia.entity.DetallePedido;

@Repository
public interface DetallePedidoDao  extends JpaRepository<DetallePedido, Integer>{

}
