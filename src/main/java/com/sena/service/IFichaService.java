package com.sena.service;

import java.util.List;

import com.sena.modelo.Ficha;

public interface IFichaService extends IService<Ficha>{

	public List<Ficha> listarTodosFiltro(String numero, String jornada, int idDetalleEstado);
	
	public boolean existeNumero(String numero);
}
