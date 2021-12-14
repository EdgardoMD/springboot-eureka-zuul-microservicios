package com.emd.commons.alumnos.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "alumnos")
@Data
public class Alumno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty
    private String nombre;
	
	@NotEmpty
    private String apellido;
	
	@NotEmpty
	@Email
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    
    @Lob
    @JsonIgnore
    private byte[] foto;

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }
    
    public Integer getFotoHashCode() {
    	return (this.foto != null) ? this.foto.hashCode(): null;
    }
    
    @Override
    public boolean equals(Object object) {
    	if(this == object) {
    		return true;
    	}
    	if(!(object instanceof Alumno)) {
    		return false;
    	}
    	Alumno alumno = (Alumno) object;
    	return this.id != null && this.id.equals(alumno.getId());
    }
    
}
