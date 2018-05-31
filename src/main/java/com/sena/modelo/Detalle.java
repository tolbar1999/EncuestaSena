package com.sena.modelo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "detalle", catalog = "encuestasena")
public class Detalle implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
		
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	
	
	@Column(name = "descripcion", nullable = false, columnDefinition = "text")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "id_maestro", nullable = false)
	private Maestro maestro;


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Maestro getMaestro() {
		return this.maestro;
	}

	public void setMaestro(Maestro maestro) {
		this.maestro = maestro;
	}

}
