package com.sena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sena.modelo.Detalle;
import com.sena.modelo.Evaluacion;
import com.sena.modelo.Instructor;
import com.sena.modelo.Pregunta;

@Repository
public interface IEvaluacionRepository extends JpaRepository<Evaluacion, Integer>{
	
	int idDetalleEstadoSinResponder = Detalle.idDetalleEstadoSinResponder;
	
	@Query("select distinct e.instructor from Evaluacion e where e.aprendiz.id = :idAprendiz and e.detalleEstado.id = "+idDetalleEstadoSinResponder)
	public List<Instructor> obtenerInstructoresPorIdAprendiz(@Param("idAprendiz") int idAprendiz);  
	
	@Query("select distinct e.pregunta from Evaluacion e where e.aprendiz.id = :idAprendiz and e.detalleEstado.id = "+idDetalleEstadoSinResponder)
	public List<Pregunta> obtenerPreguntasPorIdAprendiz(@Param("idAprendiz") int idAprendiz); 
	
	@Query("select distinct e.detallePeriodo from Evaluacion e where e.aprendiz.id = :idAprendiz and e.detalleEstado.id = "+idDetalleEstadoSinResponder)
	public Detalle obtenerPeriodoPorIdAprendiz(@Param("idAprendiz") int idAprendiz);
}
