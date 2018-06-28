package com.sena.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sena.modelo.Ficha;

public interface IFichaService extends IService<Ficha>{

	public List<Ficha> listarTodosFiltro(String numero, String jornada, int idDetalleEstado);
	
	public boolean existeNumero(String numero);
	
	public Page<String[]> autoCompletarNumeroFicha(String numero, Pageable pageable);
	 
	public Integer buscarPorNumeroFicha(String numero);
	
	public boolean existeNumeroInstructorFicha(String numero);
	
}
