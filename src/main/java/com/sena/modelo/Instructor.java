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
@Table(name = "instructor", catalog = "encuestasena")
public class Instructor implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@Column(name = "cedula", unique = true, nullable = false, length = 15)
	private String cedula;
	
	@Column(name = "nombre_completo", nullable = false, length = 60)
	private String nombreCompleto;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_instructor", nullable = false)
	private Detalle detalleTipoInstructor;
	
	@ManyToOne
	@JoinColumn(name = "id_estado", nullable = false)
	private Detalle detalleEstado;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Detalle getDetalleTipoInstructor() {
		return detalleTipoInstructor;
	}

	public void setDetalleTipoInstructor(Detalle detalleTipoInstructor) {
		this.detalleTipoInstructor = detalleTipoInstructor;
	}

	public Detalle getDetalleEstado() {
		return detalleEstado;
	}

	public void setDetalleEstado(Detalle detalleEstado) {
		this.detalleEstado = detalleEstado;
	}
	
}
