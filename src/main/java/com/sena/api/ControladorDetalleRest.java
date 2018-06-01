package com.sena.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.modelo.Detalle;
import com.sena.service.IDetalleService;

@RestController
@RequestMapping("/detalle")
public class ControladorDetalleRest {

	@Autowired
	private IDetalleService servicioDetalle;
	
	
	
	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Detalle> listarTodos(){
				
		return servicioDetalle.listarTodos();		
	}
	
	
	@RequestMapping(value = "/listarPorId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Detalle listarPorId(@RequestParam int id) {
		
		return servicioDetalle.listarPorId(id);
	}
	
	
	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public void insertar(@RequestBody Detalle detalle) {
		
		servicioDetalle.insertar(detalle);
	}
	

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public void actualizar(@RequestBody Detalle detalle) {
		
		servicioDetalle.actualizar(detalle);
	}
	
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
	public void eliminar(@RequestParam int id) {
		
		servicioDetalle.eliminar(id);
	}
	
	@RequestMapping(value = "/listarTodosFiltro", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Detalle> listarTodosFiltro(@RequestParam String nombre, @RequestParam int idMaestro){
				
		return servicioDetalle.listarTodosFiltro(nombre, idMaestro);
	}
	
}
