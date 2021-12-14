package com.emd.commons.examenes.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "examenes")
@Data
public class Examen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min = 4, max = 30)
	private String nombre;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@JsonIgnoreProperties(value = {"examen"}, allowSetters = true)
	@OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pregunta> preguntas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Asignatura asignatura;
	
	@Transient
	private boolean respondido;
	
	public Examen() {
		this.preguntas = new ArrayList<>();
	}
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas.clear();
		preguntas.forEach(this::addPregunta); //con esta sintaxis se asume que cada pregunta que se recibe como argumento, tambien se pasa como argumento
	}
	
	public void addPregunta(Pregunta pregunta) {
		this.preguntas.add(pregunta);
		pregunta.setExamen(this);
	}
	
	public void removePregunta(Pregunta pregunta) {
		this.preguntas.remove(pregunta);
		pregunta.setExamen(null);
	}
	
	 @Override
	    public boolean equals(Object object) {
	    	if(this == object) {
	    		return true;
	    	}
	    	if(!(object instanceof Examen)) {
	    		return false;
	    	}
	    	Examen examen = (Examen) object;
	    	return this.id != null && this.id.equals(examen.getId());
	    }

}
