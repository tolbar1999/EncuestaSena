package com.sena.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Ficha;
import com.sena.repository.IFichaRepository;
import com.sena.service.IFichaService;

@Service
public class FichaServiceImpl implements IFichaService{

	
	@Autowired
	private IFichaRepository repositorioFicha;
	
	
	
	@Override
	public List<Ficha> listarTodos() {

		return repositorioFicha.findAll();
	}

	@Override
	public Ficha listarPorId(int id) {

		return repositorioFicha.findOne(id);
	}

	@Override
	public void insertar(Ficha entidad) {

		repositorioFicha.save(entidad);
	}

	@Override
	public void actualizar(Ficha entidad) {

		repositorioFicha.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioFicha.delete(id);
	}

}
