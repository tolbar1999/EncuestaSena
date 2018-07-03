package com.sena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Aprendiz;
import com.sena.modelo.Detalle;

@Repository
public interface IAprendizRepository extends JpaRepository<Aprendiz, Integer>{

	int idDetalleEstadoActivo = Detalle.idDetalleEstadoActivo;
	
	@Query("select a.id from Aprendiz a where a.identificacion = :identificacion")
	public Integer existeIdentificacion(@Param("identificacion") String identificacion); 
	
	@Query("select a.id from Aprendiz a where a.identificacion = :identificacion and a.detalleEstado.id = "+idDetalleEstadoActivo)
	public Integer existeIdentificacionAprendiz(@Param("identificacion") String identificacion); 
	
	@Query("from Aprendiz a where a.identificacion = :identificacion and a.detalleEstado.id = "+idDetalleEstadoActivo)
	public Aprendiz listarPorIdentificacion(@Param("identificacion") String identificacion);
}
