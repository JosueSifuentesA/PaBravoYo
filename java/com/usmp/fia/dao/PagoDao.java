package com.usmp.fia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usmp.fia.entity.TipoPago;

public interface PagoDao extends JpaRepository<TipoPago, Integer> {

}
