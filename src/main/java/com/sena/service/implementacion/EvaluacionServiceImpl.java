package com.sena.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Evaluacion;
import com.sena.repository.IEvaluacionRepository;
import com.sena.service.IEvaluacionService;

@Service
public class EvaluacionServiceImpl implements IEvaluacionService{

	
	@Autowired
	private IEvaluacionRepository repositorioEvaluacion;

	
	
	@Override
	public List<Evaluacion> listarTodos() {

		return repositorioEvaluacion.findAll();
	}

	@Override
	public Evaluacion listarPorId(int id) {

		return repositorioEvaluacion.findOne(id);
	}

	@Override
	public void insertar(Evaluacion entidad) {

		repositorioEvaluacion.save(entidad);
	}

	@Override
	public void actualizar(Evaluacion entidad) {

		repositorioEvaluacion.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioEvaluacion.delete(id);
	}

}
