package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Detalle;

@Repository
public interface IDetalleRepository extends JpaRepository<Detalle, Integer>{

	
	
}
