package com.sena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Detalle;

@Repository
public interface IDetalleRepository extends JpaRepository<Detalle, Integer>{

	@Query("from Detalle d where d.maestro.id = :idMaestro")
	public List<Detalle> listarTodosPorIdMaestro(@Param("idMaestro") int idMaestro);
	
}
