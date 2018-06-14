package com.sena.service;

import java.util.List;

import com.sena.modelo.Pregunta;

public interface IPreguntaService extends IService<Pregunta>{

	public List<Pregunta> listarTodosFiltro(int idDetalleTipoFormacion, int idDetalleEstado);
}
