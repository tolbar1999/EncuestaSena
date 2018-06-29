package com.sena.service;

import java.util.List;

import com.sena.modelo.Aprendiz;

public interface IAprendizService extends IService<Aprendiz>{

	public boolean existeIdentificacion(String identificacion);
	
	public List<Aprendiz> listarTodosFiltro(String identificacion, String nombreCompleto, int idFicha, int idDetalleEstado);
	
	public boolean existeIdentificacionAprendiz(String identificacion);
}

