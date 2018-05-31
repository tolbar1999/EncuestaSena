package com.sena.modelo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "evaluacion", catalog = "encuestasena")
public class Evaluacion implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idAprendiz", column = @Column(name = "id_aprendiz", nullable = false)),
			@AttributeOverride(name = "idInstructor", column = @Column(name = "id_instructor", nullable = false)),
			@AttributeOverride(name = "idPregunta", column = @Column(name = "id_pregunta", nullable = false)),
			@AttributeOverride(name = "idPeriodo", column = @Column(name = "id_periodo", nullable = false)) 
	})
	private EvaluacionId id;
	
	
	@ManyToOne
	@JoinColumn(name = "id_aprendiz", nullable = false, insertable = false, updatable = false)
	private Aprendiz aprendiz;
	
	@ManyToOne
	@JoinColumn(name = "id_instructor", nullable = false, insertable = false, updatable = false)
	private Instructor instructor;
	
	@ManyToOne
	@JoinColumn(name = "id_pregunta", nullable = false, insertable = false, updatable = false)
	private Pregunta pregunta;
	
	@ManyToOne
	@JoinColumn(name = "id_periodo", nullable = false, insertable = false, updatable = false)
	private Detalle detallePeriodo;
	
	@ManyToOne
	@JoinColumn(name = "id_estado", nullable = false)
	private Detalle detalleEstado;
	
	@Column(name = "respuesta", nullable = false, length = 30)
	private String respuesta;
	
	@Column(name = "observaciones", nullable = false, columnDefinition = "text")
	private String observaciones;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false, length = 19, columnDefinition = "datetime default current_timestamp")
	private Date fecha;

	
	
	public Aprendiz getAprendiz() {
		return aprendiz;
	}

	public void setAprendiz(Aprendiz aprendiz) {
		this.aprendiz = aprendiz;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public Detalle getDetallePeriodo() {
		return detallePeriodo;
	}

	public void setDetallePeriodo(Detalle detallePeriodo) {
		this.detallePeriodo = detallePeriodo;
	}

	public Detalle getDetalleEstado() {
		return detalleEstado;
	}

	public void setDetalleEstado(Detalle detalleEstado) {
		this.detalleEstado = detalleEstado;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public EvaluacionId getId() {
		return id;
	}

	public void setId(EvaluacionId id) {
		this.id = id;
	}

	
}
