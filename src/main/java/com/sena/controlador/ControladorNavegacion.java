package com.sena.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorNavegacion {

	
	@RequestMapping(value = {"/","/*"})
	public String navegarIndex() {
		 
		return "aprendiz/index";
	} 
	
	@RequestMapping(value = "/encuesta") 
	public String navegarEncuesta() {
		
		return "aprendiz/encuesta";
	} 
	
	
	@RequestMapping(value = "/administrador")
	public String navegarAdministrador() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.getName().equals("anonymousUser")) {

			return "administrador/login";

		} else {

			return "administrador/index";
		}
	}
	
	@RequestMapping(value = "/gestionar-maestro-detalle")
	public String navegarGestionarMaestroDetalle() {
		
		return "administrador/componentes/gestionar-maestro-detalle";
	}
	
	@RequestMapping(value = "/gestionar-ficha")
	public String navegarGestionarFicha() {
		
		return "administrador/componentes/gestionar-ficha";
	}
	
	@RequestMapping(value = "/gestionar-instructor")
	public String navegarGestionarInstructor() {
		
		return "administrador/componentes/gestionar-instructor";
	}
	
	@RequestMapping(value = "/gestionar-pregunta")
	public String navegarGestionarPregunta() {
		
		return "administrador/componentes/gestionar-pregunta";
	}
	
	@RequestMapping(value = "/gestionar-aprendiz")
	public String navegarGestionarAprendiz() {
		
		return "administrador/componentes/gestionar-aprendiz";
	}
	
	@RequestMapping(value = "/gestionar-evaluacion")
	public String navegarGestionarEvaluacion() {
		
		return "administrador/componentes/gestionar-evaluacion";
	}
	 
	@RequestMapping(value = "/asignar-ficha-instructor")
	public String navegarAsignarFichaInstructor() {
		
		return "administrador/componentes/asignar-ficha-instructor";
	}
	

}
