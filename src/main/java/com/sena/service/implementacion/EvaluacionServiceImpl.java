package com.sena.service.implementacion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Detalle;
import com.sena.modelo.Evaluacion;
import com.sena.repository.IEvaluacionRepository;
import com.sena.service.IEvaluacionService;

@Service
public class EvaluacionServiceImpl implements IEvaluacionService{

	
	@Autowired
	private IEvaluacionRepository repositorioEvaluacion;

	@PersistenceContext
	private EntityManager em;
	
	int idDetalleEstadoActivo = Detalle.idDetalleEstadoActivo;
	int idDetalleEstadoSinResponder = Detalle.idDetalleEstadoSinResponder;
	
	
	
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

	@Transactional
	@Override 
	public void preInsertar(int idDetallePeriodo, int idDetalleTipoFormacion) {
		
		String query = 
		"insert into evaluacion (id_aprendiz,id_instructor,id_pregunta,id_periodo, "+
		"id_estado,respuesta,observaciones) "+
		"select a.id as id_aprendiz, i.id as id_instructor, "+
		"p.id as id_pregunta, dp.id as id_periodo,"+idDetalleEstadoSinResponder+" as id_estado, '' as respuesta, '' as observaciones from ficha as f "+
		
		"inner join aprendiz as a on a.id_ficha = f.id "+ 
		"inner join instructor_ficha as insf on insf.id_ficha = f.id "+ 
		"inner join instructor as i on i.id = insf.id_instructor "+  
		"inner join pregunta as p on p.id_tipo_formacion = "+idDetalleTipoFormacion+" "+
		"inner join detalle as dp on dp.id = "+idDetallePeriodo+" "+
		
		"where f.id_tipo_formacion = "+idDetalleTipoFormacion+" and f.id_estado = "+idDetalleEstadoActivo+" "+
		"and a.id_estado = "+idDetalleEstadoActivo+" and i.id_estado = "+idDetalleEstadoActivo+" and p.id_estado = "+idDetalleEstadoActivo+" "+
		
		"order by a.nombre_completo";
		
					   
		em.createNativeQuery(query).executeUpdate();  
		 
	}

}
