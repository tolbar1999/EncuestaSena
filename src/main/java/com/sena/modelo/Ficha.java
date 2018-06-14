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
@Table(name = "ficha", catalog = "encuestasena")
public class Ficha implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@Column(name = "numero", unique = true, nullable = false, length = 10)
	private String numero;
	
	@Column(name = "jornada", nullable = false, length = 10)
	private String jornada;
	
	@ManyToOne
	@JoinColumn(name = "id_sede", nullable = false)
	private Detalle detalleSede;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_formacion", nullable = false)
	private Detalle detalleTipoFormacion;
	
	@ManyToOne
	@JoinColumn(name = "id_estado", nullable = false)
	private Detalle detalleEstado;

	
	
	
	public Integer getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getJornada() {
		return jornada;
	}

	public Detalle getDetalleSede() {
		return detalleSede;
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

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public void setDetalleSede(Detalle detalleSede) {
		this.detalleSede = detalleSede;
	}

	public void setDetalleTipoFormacion(Detalle detalleTipoFormacion) {
		this.detalleTipoFormacion = detalleTipoFormacion;
	}

	public void setDetalleEstado(Detalle detalleEstado) {
		this.detalleEstado = detalleEstado;
	}

}
