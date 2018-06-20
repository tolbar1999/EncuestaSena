package com.sena.service;

import java.util.List;

import com.sena.modelo.InstructorFicha;
import com.sena.modelo.InstructorFichaMod;

public interface IInstructorFichaService extends IService<InstructorFicha>{

	public boolean insertarDevolver(InstructorFicha instructorFicha);
	
	public void actualizarCustom(InstructorFichaMod instructorFichaMod);
	
	public List<InstructorFicha> listarTodosFiltro(String numeroFicha, String nombreInstructor);
}
