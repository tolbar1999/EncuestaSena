package com.sena.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Maestro;
import com.sena.repository.IMaestroRepository;
import com.sena.service.IMaestroService;

@Service
public class MaestroServiceImpl implements IMaestroService{

	
	@Autowired
	private IMaestroRepository repositorioMaestro;
	
	
	@Override
	public List<Maestro> listarTodos() {

		return repositorioMaestro.findAll();
	}

	@Override
	public Maestro listarPorId(int id) {

		return repositorioMaestro.findOne(id);
	}

	@Override
	public void insertar(Maestro entidad) {

		repositorioMaestro.save(entidad);
	}

	@Override
	public void actualizar(Maestro entidad) {

		repositorioMaestro.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioMaestro.delete(id);
	}

}
