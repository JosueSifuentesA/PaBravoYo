package com.usmp.fia.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.usmp.fia.entity.Pedido;
import com.usmp.fia.entity.Usuario;
@Repository
public interface PedidoDao extends JpaRepository<Pedido, Integer> {
	List<Pedido> findByUsuario(Usuario usuario);
}
