package com.sena.service.implementacion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.InstructorFicha;
import com.sena.modelo.InstructorFichaId;
import com.sena.modelo.InstructorFichaMod;
import com.sena.repository.IInstructorFichaRepository;
import com.sena.service.IInstructorFichaService;

@Service
public class InstructorFichaServiceImpl implements IInstructorFichaService{

	
	@Autowired
	private IInstructorFichaRepository repositorioInstructorFicha;
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	@Override
	public List<InstructorFicha> listarTodos() {

		return repositorioInstructorFicha.findAll();
	}

	@Override
	public InstructorFicha listarPorId(int id) {
		return null;
	}

	@Override
	public void insertar(InstructorFicha entidad) {
		
		repositorioInstructorFicha.save(entidad);
	}  

	@Override
	public void actualizar(InstructorFicha entidad) {

		repositorioInstructorFicha.save(entidad);
	}

	@Override
	public void eliminar(int id) {
		// Nada
	}

	@Override
	public boolean insertarDevolver(InstructorFicha instructorFicha) {
		
		boolean existe = repositorioInstructorFicha.exists(instructorFicha.getId());
		
		boolean devolver = false;
		
		if(!existe) {
			
			devolver = true;
			insertar(instructorFicha);
		}
		
		return devolver;
	}

	@Override
	public void actualizarCustom(InstructorFichaMod instructorFichaMod) {

		int idFichaNuevo = instructorFichaMod.getInstructorFichaNuevo().getId().getIdFicha();
		int idInstructorNuevo = instructorFichaMod.getInstructorFichaNuevo().getId().getIdInstructor();
		int idDetalleNuevo = instructorFichaMod.getInstructorFichaNuevo().getDetalleEstado().getId();
		
		InstructorFichaId instructorFichaIdViejo = instructorFichaMod.getInstructorFichaIdViejo();
		
		
		repositorioInstructorFicha.actualizarCustom(idFichaNuevo,idInstructorNuevo,idDetalleNuevo,instructorFichaIdViejo);
	}

	@Override
	public List<InstructorFicha> listarTodosFiltro(String numeroFicha, String nombreInstructor) {

		String consulta = "from InstructorFicha";
		boolean variable = false;

		TypedQuery<InstructorFicha> query = em.createQuery(consulta, InstructorFicha.class);

		
		if (numeroFicha != "" || nombreInstructor != "") {

			consulta = "from InstructorFicha if where ";

			if (numeroFicha != "") {

				variable = true;
				consulta += "if.ficha.numero = :numeroFicha ";
			}
			
			if (nombreInstructor != "") {

				if (variable) {

					consulta += "and ";
				}
				
				variable = true;
				consulta += "if.instructor.nombreCompleto = :nombreInstructor ";
			}

			query = em.createQuery(consulta, InstructorFicha.class);

			if (numeroFicha != "") {

				query.setParameter("numeroFicha", numeroFicha);
			}
			
			if (nombreInstructor != "") {

				query.setParameter("nombreInstructor", nombreInstructor);
			}

		}

		
		return query.getResultList();
	}

}
