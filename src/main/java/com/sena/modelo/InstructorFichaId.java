package com.sena.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;



@Embeddable
public class InstructorFichaId implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int idInstructor;
	private int idFicha;

	
	public InstructorFichaId() {
	}

	public InstructorFichaId(int idInstructor, int idFicha) {
		this.idInstructor = idInstructor;
		this.idFicha = idFicha;
	}

	
	public int getIdInstructor() {
		return this.idInstructor;
	}

	public void setIdInstructor(int idInstructor) {
		this.idInstructor = idInstructor;
	}

	public int getIdFicha() {
		return this.idFicha;
	}

	public void setIdFicha(int idFicha) {
		this.idFicha = idFicha;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof InstructorFichaId))
			return false;
		InstructorFichaId castOther = (InstructorFichaId) other;

		return (this.getIdInstructor() == castOther.getIdInstructor()) && (this.getIdFicha() == castOther.getIdFicha());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdInstructor();
		result = 37 * result + this.getIdFicha();
		return result;
	}
	
}
