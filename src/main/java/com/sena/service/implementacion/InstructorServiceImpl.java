package com.sena.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Instructor;
import com.sena.repository.IInstructorRepository;
import com.sena.service.IInstructorService;

@Service
public class InstructorServiceImpl implements IInstructorService{

	
	@Autowired
	private IInstructorRepository repositorioInstructor;
	
	
	@Override
	public List<Instructor> listarTodos() {

		return repositorioInstructor.findAll();
	}

	@Override
	public Instructor listarPorId(int id) {

		return repositorioInstructor.findOne(id);
	}

	@Override
	public void insertar(Instructor entidad) {

		repositorioInstructor.save(entidad);
	}

	@Override
	public void actualizar(Instructor entidad) {

		repositorioInstructor.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioInstructor.delete(id);
	}
	
}
