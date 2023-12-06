package com.usmp.fia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usmp.fia.entity.CategoriaPlato;

public interface CategoriaDao extends JpaRepository<CategoriaPlato, Integer> {

}
