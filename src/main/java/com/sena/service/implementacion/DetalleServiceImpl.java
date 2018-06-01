package com.sena.service.implementacion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Detalle;
import com.sena.repository.IDetalleRepository;
import com.sena.service.IDetalleService;

@Service
public class DetalleServiceImpl implements IDetalleService{

	
	@Autowired 
	private IDetalleRepository repositorioDetalle;
	
	@PersistenceContext
	private EntityManager em;
	
	 
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

	@Override
	public List<Detalle> listarTodosFiltro(String nombre, int idMaestro) {
		 
		String consulta = "from Detalle";
		boolean variable = false;

		TypedQuery<Detalle> query = em.createQuery(consulta, Detalle.class);

		
		if (nombre != "" || idMaestro != 0) {

			consulta = "from Detalle d where ";

			if (nombre != "") {

				variable = true;
				consulta += "d.nombre like :nombre ";
			}

			if (idMaestro != 0) {

				if (variable) {

					consulta += "and ";
				}

				consulta += "d.maestro.id = :idMaestro";
			}

			query = em.createQuery(consulta, Detalle.class);

			if (nombre != "") {

				query.setParameter("nombre", "%"+nombre+"%");
			}

			if (idMaestro != 0) {

				query.setParameter("idMaestro", idMaestro);
			}

		}

		
		return query.getResultList();
	}

}
