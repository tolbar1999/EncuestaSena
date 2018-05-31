package com.sena.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Detalle;
import com.sena.repository.IDetalleRepository;
import com.sena.service.IDetalleService;

@Service
public class DetalleServiceImpl implements IDetalleService{

	
	@Autowired
	private IDetalleRepository repositorioDetalle;
	
	
	@Override
	public List<Detalle> listarTodos() {

		return repositorioDetalle.findAll();
	}

	@Override
	public Detalle listarPorId(int id) {

		return repositorioDetalle.findOne(id);
	}

	@Override
	public void insertar(Detalle entidad) {

		repositorioDetalle.save(entidad);
	}

	@Override
	public void actualizar(Detalle entidad) {

		repositorioDetalle.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioDetalle.delete(id);
	}

}
