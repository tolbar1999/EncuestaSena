package com.sena.service;

import java.util.List;

import com.sena.modelo.Detalle;
import com.sena.modelo.Evaluacion;
import com.sena.modelo.Instructor;
import com.sena.modelo.Pregunta;

public interface IEvaluacionService extends IService<Evaluacion>{

	public void preInsertar(int idDetallePeriodo,int idDetalleTipoFormacion);
	
	public List<Instructor> obtenerInstructoresPorIdAprendiz(int idAprendiz);
	
	public List<Pregunta> obtenerPreguntasPorIdAprendiz(int idAprendiz);  
	
	public Detalle obtenerPeriodoPorIdAprendiz(int idAprendiz); 
	
	public void actualizarCustom(Evaluacion[] evaluacion);
}
