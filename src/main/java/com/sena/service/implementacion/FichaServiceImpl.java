package com.sena.service.implementacion;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Ficha;
import com.sena.repository.IFichaRepository;
import com.sena.service.IFichaService;

@Service
public class FichaServiceImpl implements IFichaService{

	
	@Autowired
	private IFichaRepository repositorioFicha;
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	@Override
	public List<Ficha> listarTodos() {

		return repositorioFicha.findAll();
	}

	@Override
	public Ficha listarPorId(int id) {

		return repositorioFicha.findOne(id);
	}

	@Override
	public void insertar(Ficha entidad) {

		repositorioFicha.save(entidad);
	}

	@Override
	public void actualizar(Ficha entidad) {

		repositorioFicha.save(entidad);
	}

	@Override
	public void eliminar(int id) {

		repositorioFicha.delete(id);
	}
	
	@Override
	public List<Ficha> listarTodosFiltro(String numero, String jornada, int idDetalleEstado) {

		String consulta = "from Ficha";
		boolean variable = false;

		TypedQuery<Ficha> query = em.createQuery(consulta, Ficha.class);

		
		if (numero != "" || jornada != "" || idDetalleEstado != 0) {

			consulta = "from Ficha f where ";

			if (numero != "") {

				variable = true;
				consulta += "f.numero like :numero ";
			}
			
			if (jornada != "") {

				if (variable) {

					consulta += "and ";
				}
				
				variable = true;
				consulta += "f.jornada = :jornada ";
			}
			
			if (idDetalleEstado != 0) {

				if (variable) {

					consulta += "and ";
				}

				consulta += "f.detalleEstado.id = :idDetalleEstado";
			}

			query = em.createQuery(consulta, Ficha.class);

			if (numero != "") {

				query.setParameter("numero", "%"+numero+"%");
			}
			
			if (jornada != "") {

				query.setParameter("jornada", jornada);
			}

			if (idDetalleEstado != 0) {

				query.setParameter("idDetalleEstado", idDetalleEstado);
			}

		}

		
		return query.getResultList();
	}

	@Override
	public boolean existeNumero(String numero) {

		boolean devolver = true;
		
		if(repositorioFicha.existeNumero(numero) != null) {
			
			devolver = false;
		}
		
		return devolver;
	}

}
