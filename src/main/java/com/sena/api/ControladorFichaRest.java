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

import com.sena.modelo.Ficha;
import com.sena.service.IFichaService;

@RestController
@RequestMapping("/ficha")
public class ControladorFichaRest {

	@Autowired
	private IFichaService servicioFicha;
	
	
	
	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ficha> listarTodos(){
				
		return servicioFicha.listarTodos();		
	}
	 
	
	@RequestMapping(value = "/listarPorId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Ficha listarPorId(@RequestParam int id) {
		
		return servicioFicha.listarPorId(id);
	}
	
	
	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public void insertar(@RequestBody Ficha ficha) {
		
		servicioFicha.insertar(ficha);
	}
	

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public void actualizar(@RequestBody Ficha ficha) {
		
		servicioFicha.actualizar(ficha);
	}
	
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
	public void eliminar(@RequestParam int id) {
		
		servicioFicha.eliminar(id);
	}	
	
	@RequestMapping(value = "/listarTodosFiltro", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Ficha> listarTodosFiltro(@RequestParam String numero, @RequestParam String jornada, @RequestParam int idDetalleEstado){
				
		return servicioFicha.listarTodosFiltro(numero,jornada,idDetalleEstado);
	}
	
	@RequestMapping(value = "/existeNumero", method = RequestMethod.GET)
	public boolean existeNumero(@RequestParam String numero) {
		
		return servicioFicha.existeNumero(numero);
	}
	
	@RequestMapping(value = "/autoCompletarNumeroFicha", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<String[]> autoCompletarNumeroFicha(@RequestParam String numero, Pageable pageable){
				
		return servicioFicha.autoCompletarNumeroFicha(numero,pageable);
	}
	
	@RequestMapping(value = "/buscarPorNumeroFicha", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer buscarPorNumeroFicha(@RequestParam String numero) {
		
		return servicioFicha.buscarPorNumeroFicha(numero);
	}
	
	@RequestMapping(value = "/existeNumeroInstructorFicha", method = RequestMethod.GET)
	public boolean existeNumeroInstructorFicha(@RequestParam String numero) {
		 
		return servicioFicha.existeNumeroInstructorFicha(numero);
	}
	
}
