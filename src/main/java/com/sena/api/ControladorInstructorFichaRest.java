package com.sena.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.modelo.InstructorFicha;
import com.sena.modelo.InstructorFichaMod;
import com.sena.service.IInstructorFichaService;

@RestController
@RequestMapping("/instructorFicha")
public class ControladorInstructorFichaRest {

	@Autowired
	private IInstructorFichaService servicioInstructorFicha;
	
	
	
	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<InstructorFicha> listarTodos(){
				
		return servicioInstructorFicha.listarTodos();		
	}
	
	
	@RequestMapping(value = "/listarPorId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public InstructorFicha listarPorId(@RequestParam int id) {
		
		return servicioInstructorFicha.listarPorId(id);
	}
	
	
	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public void insertar(@RequestBody InstructorFicha instructorFicha) {
		
		servicioInstructorFicha.insertar(instructorFicha);
	}
	

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public void actualizar(@RequestBody InstructorFicha instructorFicha) {
		
		servicioInstructorFicha.actualizar(instructorFicha);
	}
	
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
	public void eliminar(@RequestParam int id) {
		
		servicioInstructorFicha.eliminar(id);
	}	
	
	
	@RequestMapping(value = "/insertarDevolver", method = RequestMethod.POST)
	public boolean insertarDevolver(@RequestBody InstructorFicha instructorFicha) {
		 
		return servicioInstructorFicha.insertarDevolver(instructorFicha);
	}
	
	@RequestMapping(value = "/actualizarCustom", method = RequestMethod.PUT)
	public void actualizarCustom(@RequestBody InstructorFichaMod instructorFichaMod) {
		
		servicioInstructorFicha.actualizarCustom(instructorFichaMod);
	}
	
	@RequestMapping(value = "/listarTodosFiltro", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<InstructorFicha> listarTodosFiltro(@RequestParam String numeroFicha, @RequestParam String nombreInstructor){
				
		return servicioInstructorFicha.listarTodosFiltro(numeroFicha,nombreInstructor); 
	}
	
}
