package com.emd.commons.examenes.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "preguntas")
@Data
public class Pregunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String texto;
	
	@JsonIgnoreProperties(value = {"preguntas"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "examen_id")
	private Examen examen;
	
	@Override
    public boolean equals(Object object) {
    	if(this == object) {
    		return true;
    	}
    	if(!(object instanceof Pregunta)) {
    		return false;
    	}
    	Pregunta pregunta = (Pregunta) object;
    	return this.id != null && this.id.equals(pregunta.getId());
    }
	

}
