package com.ram.microservicios.app.usuarios.controllers;

import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ram.microservicios.app.usuarios.services.IAlumnoService;
import com.ram.microservicios.commons.alumnos.models.entity.Alumno;
import com.ram.microservicios.commons.controllers.CommonController;

@RestController
public class AlumnoController extends CommonController<Alumno, IAlumnoService> {
	
		
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Alumno alumno){
		
		Optional<Alumno> alumnoOptional = service.findOne(id);
		if (alumnoOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDB = alumnoOptional.get();
		
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
		
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}
	
}
