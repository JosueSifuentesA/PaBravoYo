package com.usmp.fia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usmp.fia.entity.Plato;
@Repository
public interface PlatoDao extends JpaRepository<Plato, Integer>{

}
