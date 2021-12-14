package com.emd.microservicios.cursos.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.emd.commons.alumnos.models.entity.Alumno;
import com.emd.commons.examenes.models.entity.Examen;

import lombok.Data;

@Entity
@Table(name = "cursos")
@Data
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Alumno> alumnos;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Examen> examenes;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public Curso() {
		this.alumnos = new ArrayList<>();
		this.examenes = new ArrayList<>();
	}
	
	public void addAlumno(Alumno alumno) {
		this.alumnos.add(alumno);
	}
	
	public void removeAlumno(Alumno alumno) {
		this.alumnos.remove(alumno);
	}
	
	public void addExamen(Examen examen) {
		this.examenes.add(examen);
	}
	
	public void removeExamen(Examen examen) {
		this.examenes.remove(examen);
	}
	
	

}
