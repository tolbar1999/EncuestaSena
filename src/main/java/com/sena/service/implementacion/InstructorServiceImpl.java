package com.sena.service.implementacion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Instructor;
import com.sena.repository.IInstructorRepository;
import com.sena.service.IInstructorService;

@Service
public class InstructorServiceImpl implements IInstructorService{

	
	@Autowired
	private IInstructorRepository repositorioInstructor;
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<Instructor> listarTodos() {

		return repositorioInstructor.findAll();
	}

	@Override
	public Instructor listarPorId(int id) {

		return repositorioInstructor.findOne(id);
	}

	@Override
	public void insertar(Instructor entidad) {

		repositorioInstructor.save(entidad);
	}

	@Override
	public void actualizar(Instructor entidad) {

		repositorioInstructor.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioInstructor.delete(id);
	}

	@Override
	public List<Instructor> listarTodosFiltro(String cedula, String nombreCompleto, int idDetalleTipoInstructor, int idDetalleEstado) {
		
		String consulta = "from Instructor";
		boolean variable = false;
		
		TypedQuery<Instructor> query = em.createQuery(consulta, Instructor.class);

		
		if (cedula != "" || nombreCompleto != "" || idDetalleTipoInstructor != 0 || idDetalleEstado != 0) {

			consulta = "from Instructor i where ";

			if (cedula != "") {

				variable = true;
				consulta += "i.cedula like :cedula ";
			}
			
			if (nombreCompleto != "") {

				if (variable) {

					consulta += "and ";
				}
				
				variable = true;
				consulta += "i.nombreCompleto like :nombreCompleto ";
			}
			
			if (idDetalleTipoInstructor != 0) {

				if (variable) {

					consulta += "and ";
				}
				
				variable = true;
				consulta += "i.detalleTipoInstructor.id = :idDetalleTipoInstructor ";
			}
			
			if (idDetalleEstado != 0) {

				if (variable) {

					consulta += "and ";
				}

				consulta += "i.detalleEstado.id = :idDetalleEstado";
			}

			query = em.createQuery(consulta, Instructor.class);

			if (cedula != "") {

				query.setParameter("cedula", "%"+cedula+"%");
			}
			
			if (nombreCompleto != "") {

				query.setParameter("nombreCompleto", "%"+nombreCompleto+"%");
			}

			if (idDetalleTipoInstructor != 0) {

				query.setParameter("idDetalleTipoInstructor", idDetalleTipoInstructor);
			}
			
			if (idDetalleEstado != 0) {

				query.setParameter("idDetalleEstado", idDetalleEstado);
			}

		}

		return query.getResultList();
		
	}
	
	
	@Override
	public boolean existeCedula(String cedula) {

		boolean devolver = true;
		
		if(repositorioInstructor.existeCedula(cedula) != null) {
			
			devolver = false;
		}
		
		return devolver;
	}

}
