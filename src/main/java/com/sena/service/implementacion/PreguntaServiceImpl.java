package com.sena.service.implementacion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Pregunta;
import com.sena.repository.IPreguntaRepository;
import com.sena.service.IPreguntaService;

@Service
public class PreguntaServiceImpl implements IPreguntaService{

	
	@Autowired
	private IPreguntaRepository repositorioPregunta;
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	@Override
	public List<Pregunta> listarTodos() {

		return repositorioPregunta.findAll();
	}

	@Override
	public Pregunta listarPorId(int id) {

		return repositorioPregunta.findOne(id);
	}

	@Override
	public void insertar(Pregunta entidad) {

		repositorioPregunta.save(entidad);
	}

	@Override
	public void actualizar(Pregunta entidad) {

		repositorioPregunta.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioPregunta.delete(id);
	}

	@Override
	public List<Pregunta> listarTodosFiltro(int idDetalleTipoFormacion, int idDetalleEstado) {

		String consulta = "from Pregunta";
		boolean variable = false;

		TypedQuery<Pregunta> query = em.createQuery(consulta, Pregunta.class);

		
		if (idDetalleTipoFormacion != 0 || idDetalleEstado != 0) {

			consulta = "from Pregunta p where ";

			if (idDetalleTipoFormacion != 0) {

				variable = true;
				consulta += "p.detalleTipoFormacion.id = :idDetalleTipoFormacion ";
			}
			
			if (idDetalleEstado != 0) {

				if (variable) {

					consulta += "and ";
				}

				consulta += "p.detalleEstado.id = :idDetalleEstado";
			}

			query = em.createQuery(consulta, Pregunta.class);

			if (idDetalleTipoFormacion != 0) {

				query.setParameter("idDetalleTipoFormacion", idDetalleTipoFormacion);
			}
		
			if (idDetalleEstado != 0) {

				query.setParameter("idDetalleEstado", idDetalleEstado);
			}

		}

		
		return query.getResultList();
		
	}
	

}
