package com.emd.microservicios.respuestas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emd.microservicios.respuestas.models.entity.Respuesta;
import com.emd.microservicios.respuestas.services.IRespuestaService;

@RestController
public class RespuestaController {
	
	@Autowired
	private IRespuestaService respuestaService;
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas){
		Iterable<Respuesta> respuestasDb = respuestaService.saveAll(respuestas);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestasDb);	
	}
	
	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")
	public ResponseEntity<?> obtenerRespuestaPorAlumnoPorExamen(@PathVariable Long alumnoId, @PathVariable Long examenId){
		Iterable<Respuesta> respuestas = respuestaService.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		return ResponseEntity.ok(respuestas);
	}
	
	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")
	public ResponseEntity<?> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Long alumnoId){
		Iterable<Long> examenesIds = respuestaService.findExamenesIdsConRespuestasByAlumno(alumnoId);
		return ResponseEntity.ok(examenesIds);
	}
}
