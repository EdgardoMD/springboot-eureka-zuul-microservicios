package com.emd.microservicios.examenes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emd.commons.examenes.models.entity.Asignatura;
import com.emd.commons.examenes.models.entity.Examen;
import com.emd.commons.microservicios.services.CommonServiceImpl;
import com.emd.microservicios.examenes.models.repository.IAsignaturaRepository;
import com.emd.microservicios.examenes.models.repository.IExamenRepository;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, IExamenRepository> 
							implements IExamenService{
	
	@Autowired
	private IAsignaturaRepository asignaturaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String texto) {
		
		return repository.findByNombre(texto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Asignatura> findAllAsignaturas() {
		return (List<Asignatura>) asignaturaRepository.findAll();
	}

}
