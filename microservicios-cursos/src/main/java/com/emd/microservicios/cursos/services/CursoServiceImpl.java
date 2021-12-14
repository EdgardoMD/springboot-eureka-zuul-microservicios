package com.emd.microservicios.cursos.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emd.commons.microservicios.services.CommonServiceImpl;
import com.emd.microservicios.cursos.models.entity.Curso;
import com.emd.microservicios.cursos.models.repository.ICursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, ICursoRepository> implements ICursoService {

	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}


}
