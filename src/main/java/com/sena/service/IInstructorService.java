package com.sena.service;

import java.util.List;

import com.sena.modelo.Instructor;

public interface IInstructorService extends IService<Instructor>{
	
	public List<Instructor> listarTodosFiltro(String cedula, String nombreCompleto, int idDetalleTipoInstructor, int idDetalleEstado);

	public boolean existeCedula(String cedula);
	
}
