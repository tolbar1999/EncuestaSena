package com.sena.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sena.modelo.Instructor;

public interface IInstructorService extends IService<Instructor>{
	
	public List<Instructor> listarTodosFiltro(String cedula, String nombreCompleto, int idDetalleTipoInstructor, int idDetalleEstado);

	public boolean existeCedula(String cedula);
	
	public Page<String[]> autoCompletarNombreCompleto(String nombreCompleto, Pageable pageable);
	 
	public Integer buscarPorNombreCompleto(String nombreCompleto);
	
	public boolean existeNombreCompleto(String nombreCompleto);
}
