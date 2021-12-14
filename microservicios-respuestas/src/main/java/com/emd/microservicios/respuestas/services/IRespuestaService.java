package com.emd.microservicios.respuestas.services;

import com.emd.microservicios.respuestas.models.entity.Respuesta;

public interface IRespuestaService {
	
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);

}
