package com.sena.service.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.modelo.Usuario;
import com.sena.repository.IUsuarioRepository;
import com.sena.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	
	@Autowired
	private IUsuarioRepository repositorioUsuario;
	
	
	@Override
	public List<Usuario> listarTodos() {

		return repositorioUsuario.findAll();
	}

	@Override
	public Usuario listarPorId(int id) {

		return repositorioUsuario.findOne(id);
	}

	@Override
	public void insertar(Usuario entidad) {

		repositorioUsuario.save(entidad);
	}

	@Override
	public void actualizar(Usuario entidad) {

		repositorioUsuario.save(entidad);
	}

	@Override
	public void eliminar(int id) {
		
		repositorioUsuario.delete(id);
	}

}
