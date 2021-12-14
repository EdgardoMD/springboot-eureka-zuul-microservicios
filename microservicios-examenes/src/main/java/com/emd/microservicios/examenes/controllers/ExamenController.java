package com.emd.microservicios.examenes.controllers;

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

import com.emd.commons.examenes.models.entity.Examen;
import com.emd.commons.microservicios.controllers.CommonController;
import com.emd.microservicios.examenes.services.IExamenService;

@RestController
public class ExamenController extends CommonController<Examen, IExamenService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
    		return this.validar(result);
    	}
		
		Optional<Examen> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Examen examenDb = optional.get();
		examenDb.setNombre(examen.getNombre());
		
		//Se optimiza bloque de codigo comentado mas abajo
		examenDb.getPreguntas()
		.stream()
		.filter(pdb -> !examen.getPreguntas().contains(pdb))
		.forEach(examenDb::removePregunta);
		
//		List<Pregunta> eliminadas = new ArrayList<>();
//		examenDb.getPreguntas().forEach(pdb -> {
//			if(!examen.getPreguntas().contains(pdb)) {
//				eliminadas.add(pdb);
//			}
//		});
//		eliminadas.forEach(p -> {
//			examenDb.removePregunta(p);
//		});
		
		examenDb.setPreguntas(examen.getPreguntas());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	@GetMapping("/filtrar/{texto}")
	public ResponseEntity<?> filtrar(@PathVariable String texto){
		return ResponseEntity.ok(service.findByNombre(texto));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas(){
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
}
