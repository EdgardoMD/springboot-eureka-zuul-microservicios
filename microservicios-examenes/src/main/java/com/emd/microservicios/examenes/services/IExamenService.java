package com.emd.microservicios.examenes.services;

import java.util.List;

import com.emd.commons.examenes.models.entity.Asignatura;
import com.emd.commons.examenes.models.entity.Examen;
import com.emd.commons.microservicios.services.ICommonService;

public interface IExamenService extends ICommonService<Examen>{

	public List<Examen> findByNombre(String texto);
	
	public List<Asignatura> findAllAsignaturas();
}
