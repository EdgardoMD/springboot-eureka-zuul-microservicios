package com.emd.microservicios.usuarios.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emd.commons.alumnos.models.entity.Alumno;
import com.emd.commons.microservicios.controllers.CommonController;
import com.emd.microservicios.usuarios.services.IAlumnoService;

@RestController
public class AlumnoController extends CommonController<Alumno, IAlumnoService> {
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		
		Optional<Alumno> o = service.findById(id);
		if (o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Alumno> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoEditar = o.get();
		alumnoEditar.setNombre(alumno.getNombre());
		alumnoEditar.setApellido(alumno.getApellido());
		alumnoEditar.setEmail(alumno.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoEditar));
	}
	
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.crear(alumno, result);
	}
	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, 
			@PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException {

		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Alumno> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		
		if(!archivo.isEmpty()) {
			alumnoDb.setFoto(archivo.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}

	@GetMapping("/filtrar/{texto}")
	public ResponseEntity<?> filtrar(@PathVariable String texto) {
		return ResponseEntity.ok(service.findByNombreOrApellido(texto));
	}
//Se elimininan de este controller por que ahora se usaran los common
//    @GetMapping
//    public ResponseEntity<?> listar(){
//        return ResponseEntity.ok(service.findAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> ver(@PathVariable Long id){
//        Optional<Alumno> o = service.findById(id);
//        if (o.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(o.get());
//    }
//
//    @PostMapping
//    public ResponseEntity<?> crear(@RequestBody Alumno alumno){
//        Alumno nuevoAlumno = service.save(alumno);
//        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> eliminar(@PathVariable Long id){
//        service.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

}
