package com.sena.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class EvaluacionId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private int idAprendiz;
	private int idInstructor;
	private int idPregunta;
	private int idPeriodo;

	
	public EvaluacionId() {
	}

	public EvaluacionId(int idAprendiz, int idInstructor, int idPregunta, int idPeriodo) {
		this.idAprendiz = idAprendiz;
		this.idInstructor = idInstructor;
		this.idPregunta = idPregunta;
		this.idPeriodo = idPeriodo;
	}

	public int getIdAprendiz() {
		return this.idAprendiz;
	}

	public void setIdAprendiz(int idAprendiz) {
		this.idAprendiz = idAprendiz;
	}

	public int getIdInstructor() {
		return this.idInstructor;
	}

	public void setIdInstructor(int idInstructor) {
		this.idInstructor = idInstructor;
	}

	public int getIdPregunta() {
		return this.idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public int getIdPeriodo() {
		return this.idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EvaluacionId))
			return false;
		EvaluacionId castOther = (EvaluacionId) other;

		return (this.getIdAprendiz() == castOther.getIdAprendiz())
				&& (this.getIdInstructor() == castOther.getIdInstructor())
				&& (this.getIdPregunta() == castOther.getIdPregunta())
				&& (this.getIdPeriodo() == castOther.getIdPeriodo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdAprendiz();
		result = 37 * result + this.getIdInstructor();
		result = 37 * result + this.getIdPregunta();
		result = 37 * result + this.getIdPeriodo();
		return result;
	}

}
