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
@Table(name = "pregunta", catalog = "encuestasena")
public class Pregunta implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", nullable = false, columnDefinition = "text")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_formacion", nullable = false)
	private Detalle detalleTipoFormacion;
	
	@ManyToOne
	@JoinColumn(name = "id_estado", nullable = false)
	private Detalle detalleEstado;

	
	
	
	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Detalle getDetalleTipoFormacion() {
		return detalleTipoFormacion;
	}

	public Detalle getDetalleEstado() {
		return detalleEstado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDetalleTipoFormacion(Detalle detalleTipoFormacion) {
		this.detalleTipoFormacion = detalleTipoFormacion;
	}

	public void setDetalleEstado(Detalle detalleEstado) {
		this.detalleEstado = detalleEstado;
	}

}
