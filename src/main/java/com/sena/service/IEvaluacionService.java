package com.sena.service;

import com.sena.modelo.Evaluacion;

public interface IEvaluacionService extends IService<Evaluacion>{

	public void preInsertar(int idDetallePeriodo,int idDetalleTipoFormacion);
	
}
