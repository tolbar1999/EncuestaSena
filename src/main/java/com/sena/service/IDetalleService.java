package com.sena.service;

import java.util.List;

import com.sena.modelo.Detalle;

public interface IDetalleService extends IService<Detalle>{

	public List<Detalle> listarTodosPorIdMaestro(int idMaestro);
	
	public List<Detalle> listarTodosFiltro(String nombre, int idMaestro);
	
}
