package com.emd.microservicios.usuarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emd.commons.alumnos.models.entity.Alumno;
import com.emd.commons.microservicios.services.CommonServiceImpl;
import com.emd.microservicios.usuarios.repositories.IAlumnoRepository;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService{
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String texto) {
		
		return repository.findByNombreOrApellido(texto);
	}
	

}
