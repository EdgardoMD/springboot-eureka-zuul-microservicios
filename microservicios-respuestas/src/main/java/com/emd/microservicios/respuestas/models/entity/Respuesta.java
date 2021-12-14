package com.emd.microservicios.respuestas.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.emd.commons.alumnos.models.entity.Alumno;
import com.emd.commons.examenes.models.entity.Pregunta;

import lombok.Data;

@Entity
@Table(name = "respuestas")
@Data
public class Respuesta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String texto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Alumno alumno;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Pregunta pregunta;
	

}
