package com.sena.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.modelo.Pregunta;
import com.sena.service.IPreguntaService;

@RestController
@RequestMapping("/pregunta")
public class ControladorPreguntaRest {

	@Autowired
	private IPreguntaService servicioPregunta;
	
	
	
	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pregunta> listarTodos(){
				
		return servicioPregunta.listarTodos();		
	}
	
	
	@RequestMapping(value = "/listarPorId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Pregunta listarPorId(@RequestParam int id) {
		
		return servicioPregunta.listarPorId(id);
	}
	
	
	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public void insertar(@RequestBody Pregunta pregunta) {
		
		servicioPregunta.insertar(pregunta);
	}
	

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public void actualizar(@RequestBody Pregunta pregunta) {
		
		servicioPregunta.actualizar(pregunta);
	}
	
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
	public void eliminar(@RequestParam int id) {
		
		servicioPregunta.eliminar(id);
	}	
	
}
