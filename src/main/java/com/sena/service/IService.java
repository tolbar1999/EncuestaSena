package com.sena.service;

import java.util.List;

public interface IService<T> {
	
	public List<T> listarTodos();
	
	public T listarPorId(int id);
	
	public void insertar(T entidad);
	
	public void actualizar(T entidad);
	
	public void eliminar(int id);
		
}
