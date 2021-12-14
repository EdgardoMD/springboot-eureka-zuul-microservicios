package com.emd.microservicios.cursos.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emd.commons.alumnos.models.entity.Alumno;
import com.emd.commons.examenes.models.entity.Examen;
import com.emd.commons.microservicios.controllers.CommonController;
import com.emd.microservicios.cursos.models.entity.Curso;
import com.emd.microservicios.cursos.services.ICursoService;

@RestController
public class CursoController extends CommonController<Curso, ICursoService>{

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
    		return this.validar(result);
    	}
		
		Optional<Curso> o = this.service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso dbCurso = o.get();
		dbCurso.setNombre(curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dbCurso));
	}
	
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id ){
		Optional<Curso> o = this.service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso dbCurso = o.get();
		alumnos.forEach(a ->{
			dbCurso.addAlumno(a);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dbCurso));
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumnos(@RequestBody Alumno alumno, @PathVariable Long id ){
		Optional<Curso> o = this.service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso dbCurso = o.get();
		dbCurso.removeAlumno(alumno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dbCurso));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnoId(id);
		return ResponseEntity.ok(curso);
	}
	
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id ){
		Optional<Curso> o = this.service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso dbCurso = o.get();
		
		examenes.forEach(e ->{
			dbCurso.addExamen(e);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dbCurso));
	}
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamenes(@RequestBody Examen examen, @PathVariable Long id ){
		Optional<Curso> o = this.service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Curso dbCurso = o.get();
		
		dbCurso.removeExamen(examen);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dbCurso));
	}
		
}
