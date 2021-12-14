package com.emd.microservicios.cursos.services;

import com.emd.commons.microservicios.services.ICommonService;
import com.emd.microservicios.cursos.models.entity.Curso;

public interface ICursoService extends ICommonService<Curso> {

	public Curso findCursoByAlumnoId(Long id);
}
