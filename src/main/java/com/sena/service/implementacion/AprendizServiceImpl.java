package com.sena.service.implementacion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Aprendiz;
import com.sena.repository.IAprendizRepository;
import com.sena.service.IAprendizService;

@Service
public class AprendizServiceImpl implements IAprendizService{

	 
	@Autowired
	private IAprendizRepository repositorioAprendiz;
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<Aprendiz> listarTodos() {

		return repositorioAprendiz.findAll();
	}

	@Override
	public Aprendiz listarPorId(int id) {

		return repositorioAprendiz.findOne(id);
	}

	@Override
	public void insertar(Aprendiz entidad) {

		repositorioAprendiz.save(entidad);
	}

	@Override
	public void actualizar(Aprendiz entidad) {
		
		repositorioAprendiz.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioAprendiz.delete(id);
	}

	@Override
	public boolean existeIdentificacion(String identificacion) {
		
		boolean devolver = true;
		
		if(repositorioAprendiz.existeIdentificacion(identificacion) != null) {
			
			devolver = false;
		}
		
		return devolver;
	}

	@Override
	public List<Aprendiz> listarTodosFiltro(String identificacion, String nombreCompleto, int idFicha,
			int idDetalleEstado) {
		
		String consulta = "from Aprendiz";
		boolean variable = false;
		
		TypedQuery<Aprendiz> query = em.createQuery(consulta, Aprendiz.class);
		
		
		if (identificacion != "" || nombreCompleto != "" || idFicha != 0 || idDetalleEstado != 0) {

			consulta = "from Aprendiz a where ";

			if (identificacion != "") {

				variable = true;
				consulta += "a.identificacion like :identificacion ";
			}
			
			if (nombreCompleto != "") {

				if (variable) {

					consulta += "and ";
				}
				
				variable = true;
				consulta += "a.nombreCompleto like :nombreCompleto ";
			}
			
			if (idFicha != 0) {

				if (variable) {

					consulta += "and ";
				}
				
				variable = true;
				consulta += "a.ficha.id = :idFicha ";
			}
			
			if (idDetalleEstado != 0) {

				if (variable) {

					consulta += "and ";
				}

				consulta += "a.detalleEstado.id = :idDetalleEstado";
			}

			query = em.createQuery(consulta, Aprendiz.class);

			if (identificacion != "") {

				query.setParameter("identificacion", "%"+identificacion+"%");
			}
			
			if (nombreCompleto != "") {

				query.setParameter("nombreCompleto", "%"+nombreCompleto+"%");
			}

			if (idFicha != 0) {

				query.setParameter("idFicha", idFicha);
			}
			
			if (idDetalleEstado != 0) {

				query.setParameter("idDetalleEstado", idDetalleEstado);
			}

		}

		return query.getResultList();
		
	}

	
}
