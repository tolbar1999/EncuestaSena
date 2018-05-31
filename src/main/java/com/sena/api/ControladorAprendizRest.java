package com.sena.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.modelo.Aprendiz;
import com.sena.service.IAprendizService;

@RestController
@RequestMapping("/aprendiz")
public class ControladorAprendizRest {
	
	
	@Autowired
	private IAprendizService servicioAprendiz;
	
	
	
	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Aprendiz> listarTodos(){
				
		return servicioAprendiz.listarTodos();		
	}
	
	
	@RequestMapping(value = "/listarPorId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Aprendiz listarPorId(@RequestParam int id) {
		
		return servicioAprendiz.listarPorId(id);
	}
	
	
	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public void insertar(@RequestBody Aprendiz aprendiz) {
		
		servicioAprendiz.insertar(aprendiz);
	}
	

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public void actualizar(@RequestBody Aprendiz aprendiz) {
		
		servicioAprendiz.actualizar(aprendiz);
	}
	
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
	public void eliminar(@RequestParam int id) {
		
		servicioAprendiz.eliminar(id);
	}
	
}
