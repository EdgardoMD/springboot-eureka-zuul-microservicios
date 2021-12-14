package com.emd.microservicios.usuarios.services;

import java.util.List;

import com.emd.commons.alumnos.models.entity.Alumno;
import com.emd.commons.microservicios.services.ICommonService;

public interface IAlumnoService extends ICommonService<Alumno>{

	public List<Alumno> findByNombreOrApellido(String texto);
}
