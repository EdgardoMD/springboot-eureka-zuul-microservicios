package com.emd.microservicios.examenes.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.emd.commons.examenes.models.entity.Asignatura;

public interface IAsignaturaRepository extends CrudRepository<Asignatura, Long> {

}
