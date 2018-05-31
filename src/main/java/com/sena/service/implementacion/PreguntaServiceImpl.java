package com.sena.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Pregunta;
import com.sena.repository.IPreguntaRepository;
import com.sena.service.IPreguntaService;

@Service
public class PreguntaServiceImpl implements IPreguntaService{

	
	@Autowired
	private IPreguntaRepository repositorioPregunta;
	
	
	@Override
	public List<Pregunta> listarTodos() {

		return repositorioPregunta.findAll();
	}

	@Override
	public Pregunta listarPorId(int id) {

		return repositorioPregunta.findOne(id);
	}

	@Override
	public void insertar(Pregunta entidad) {

		repositorioPregunta.save(entidad);
	}

	@Override
	public void actualizar(Pregunta entidad) {

		repositorioPregunta.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioPregunta.delete(id);
	}

}
