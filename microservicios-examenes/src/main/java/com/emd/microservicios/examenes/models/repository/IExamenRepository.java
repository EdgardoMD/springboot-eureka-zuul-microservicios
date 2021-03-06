package com.emd.microservicios.examenes.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.emd.commons.examenes.models.entity.Examen;

public interface IExamenRepository extends PagingAndSortingRepository<Examen, Long>{
	
	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> findByNombre(String texto);

}
