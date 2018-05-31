package com.sena.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Aprendiz;
import com.sena.repository.IAprendizRepository;
import com.sena.service.IAprendizService;

@Service
public class AprendizServiceImpl implements IAprendizService{

	
	@Autowired
	private IAprendizRepository repositorioAprendiz;
	
	
	@Override
	public List<Aprendiz> listarTodos() {

		return repositorioAprendiz.findAll();
	}

	@Override
	public Aprendiz listarPorId(int id) {

		return repositorioAprendiz.findOne(id);
	}

	@Override
	public void insertar(Aprendiz entidad) {

		repositorioAprendiz.save(entidad);
	}

	@Override
	public void actualizar(Aprendiz entidad) {
		
		repositorioAprendiz.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioAprendiz.delete(id);
	}

	
}
