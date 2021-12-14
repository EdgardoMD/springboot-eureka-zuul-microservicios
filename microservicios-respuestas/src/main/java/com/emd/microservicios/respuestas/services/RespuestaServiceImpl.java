package com.emd.microservicios.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emd.microservicios.respuestas.models.entity.Respuesta;
import com.emd.microservicios.respuestas.models.repository.IRespuestaRepository;

@Service
public class RespuestaServiceImpl implements IRespuestaService{
	
	@Autowired
	private IRespuestaRepository repository;

	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		
		return repository.saveAll(respuestas);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		
		return repository.findExamenesIdsConRespuestasByAlumno(alumnoId);
	}

}
