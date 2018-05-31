package com.sena.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.InstructorFicha;
import com.sena.repository.IInstructorFichaRepository;
import com.sena.service.IInstructorFichaService;

@Service
public class InstructorFichaServiceImpl implements IInstructorFichaService{

	
	@Autowired
	private IInstructorFichaRepository repositorioInstructorFicha;
	
	
	
	@Override
	public List<InstructorFicha> listarTodos() {

		return repositorioInstructorFicha.findAll();
	}

	@Override
	public InstructorFicha listarPorId(int id) {

		return repositorioInstructorFicha.findOne(id);
	}

	@Override
	public void insertar(InstructorFicha entidad) {

		repositorioInstructorFicha.save(entidad);
	}

	@Override
	public void actualizar(InstructorFicha entidad) {

		repositorioInstructorFicha.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioInstructorFicha.delete(id);
	}

}
