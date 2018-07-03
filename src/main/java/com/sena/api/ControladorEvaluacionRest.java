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
import com.sena.modelo.Evaluacion;
import com.sena.modelo.Instructor;
import com.sena.modelo.Pregunta;
import com.sena.service.IEvaluacionService;

@RestController
@RequestMapping("/evaluacion")
public class ControladorEvaluacionRest {

	@Autowired
	private IEvaluacionService servicioEvaluacion;
	
	
	
	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Evaluacion> listarTodos(){
				
		return servicioEvaluacion.listarTodos();		
	}
	
	
	@RequestMapping(value = "/listarPorId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Evaluacion listarPorId(@RequestParam int id) {
		
		return servicioEvaluacion.listarPorId(id);
	}
	
	
	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public void insertar(@RequestBody Evaluacion evualacion) {
		
		servicioEvaluacion.insertar(evualacion);
	}
	

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	public void actualizar(@RequestBody Evaluacion evualacion) {
		
		servicioEvaluacion.actualizar(evualacion);
	}
	
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
	public void eliminar(@RequestParam int id) {
		
		servicioEvaluacion.eliminar(id);
	}	
	
	@RequestMapping(value = "/preInsertar", method = RequestMethod.GET) 
	public void preInsertar(@RequestParam int idDetallePeriodo, @RequestParam int idDetalleTipoFormacion) {
		
		servicioEvaluacion.preInsertar(idDetallePeriodo,idDetalleTipoFormacion);
	}
	
	@RequestMapping(value = "/obtenerInstructoresPorIdAprendiz", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Instructor> obtenerInstructoresPorIdAprendiz(@RequestParam int idAprendiz){
				
		return servicioEvaluacion.obtenerInstructoresPorIdAprendiz(idAprendiz);	 
	}
	
	@RequestMapping(value = "/obtenerPreguntasPorIdAprendiz", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pregunta> obtenerPreguntasPorIdAprendiz(@RequestParam int idAprendiz){
				
		return servicioEvaluacion.obtenerPreguntasPorIdAprendiz(idAprendiz); 
	}
	
	@RequestMapping(value = "/obtenerPeriodoPorIdAprendiz", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Detalle obtenerPeriodoPorIdAprendiz(@RequestParam int idAprendiz){
		 
		return servicioEvaluacion.obtenerPeriodoPorIdAprendiz(idAprendiz);
	}
	
	@RequestMapping(value = "/actualizarCustom", method = RequestMethod.PUT)
	public void actualizarCustom(@RequestBody Evaluacion[] evualacion) {
		
		servicioEvaluacion.actualizarCustom(evualacion); 
	}
	
	
	
}
