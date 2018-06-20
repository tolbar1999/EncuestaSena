package com.sena.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.modelo.Instructor;
import com.sena.service.IInstructorService;

@RestController
@RequestMapping("/instructor")
public class ControladorInstructorRest {

	@Autowired
	private IInstructorService servicioInstructor;
	
	
	
	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Instructor> listarTodos(){
				
		return servicioInstructor.listarTodos();		
	}
	
	
	@RequestMapping(value = "/listarPorId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Instructor listarPorId(@RequestParam int id) {
		
		return servicioInstructor.listarPorId(id);
	}
	
	
	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public void insertar(@RequestBody Instructor instructor) {
		
		servicioInstructor.insertar(instructor);
	}
	

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public void actualizar(@RequestBody Instructor instructor) {
		
		servicioInstructor.actualizar(instructor);
	}
	
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
	public void eliminar(@RequestParam int id) {
		
		servicioInstructor.eliminar(id);
	}	
	
	@RequestMapping(value = "/listarTodosFiltro", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Instructor> listarTodosFiltro(@RequestParam String cedula, @RequestParam String nombreCompleto, @RequestParam int idDetalleTipoInstructor, @RequestParam int idDetalleEstado){
				
		return servicioInstructor.listarTodosFiltro(cedula,nombreCompleto,idDetalleTipoInstructor,idDetalleEstado);
	}
	
	@RequestMapping(value = "/existeCedula", method = RequestMethod.GET)
	public boolean existeCedula(@RequestParam String cedula) {
		 
		return servicioInstructor.existeCedula(cedula);
	}
	
	@RequestMapping(value = "/autoCompletarNombreCompleto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<String[]> autoCompletarNombreCompleto(@RequestParam String nombreCompleto, Pageable pageable){
				
		return servicioInstructor.autoCompletarNombreCompleto(nombreCompleto,pageable);
	}
	
	@RequestMapping(value = "/buscarPorNombreCompleto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer buscarPorNombreCompleto(@RequestParam String nombreCompleto) {
		
		return servicioInstructor.buscarPorNombreCompleto(nombreCompleto);
	}
	
	@RequestMapping(value = "/existeNombreCompleto", method = RequestMethod.GET)
	public boolean existeNombreCompleto(@RequestParam String nombreCompleto) {
		 
		return servicioInstructor.existeNombreCompleto(nombreCompleto);
	} 
	
}
