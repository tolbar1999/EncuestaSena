package com.sena.modelo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "instructor_ficha", catalog = "encuestasena")
public class InstructorFicha implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idInstructor", column = @Column(name = "id_instructor", nullable = false)),
			@AttributeOverride(name = "idFicha", column = @Column(name = "id_ficha", nullable = false)) 
	})
	private InstructorFichaId id;

	
	@ManyToOne
	@JoinColumn(name = "id_instructor", nullable = false, insertable = false, updatable = false)
	private Instructor instructor;
	
	
	@ManyToOne
	@JoinColumn(name = "id_ficha", nullable = false, insertable = false, updatable = false)
	private Ficha ficha;
	
	
	@ManyToOne
	@JoinColumn(name = "id_estado", nullable = false)
	private Detalle detalleEstado;


	
	public Instructor getInstructor() {
		return instructor;
	}


	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}


	public Ficha getFicha() {
		return ficha;
	}


	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}


	public Detalle getDetalleEstado() {
		return detalleEstado;
	}


	public void setDetalleEstado(Detalle detalleEstado) {
		this.detalleEstado = detalleEstado;
	}


	public InstructorFichaId getId() {
		return id;
	}


	public void setId(InstructorFichaId id) {
		this.id = id;
	}

}
