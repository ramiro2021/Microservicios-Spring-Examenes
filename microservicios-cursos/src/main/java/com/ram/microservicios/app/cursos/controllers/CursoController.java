package com.ram.microservicios.app.cursos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ram.microservicios.app.cursos.models.entity.Curso;
import com.ram.microservicios.app.cursos.services.ICursoService;
import com.ram.microservicios.commons.alumnos.models.entity.Alumno;
import com.ram.microservicios.commons.controllers.CommonController;

@RestController
public class CursoController extends CommonController<Curso, ICursoService> {
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
		Optional<Curso> cursoOptional = this.service.findOne(id);
		
		if (!cursoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoDB = cursoOptional.get();
		
		cursoDB.setNombre(curso.getNombre());
		
	
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}
	
	
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable long id){
Optional<Curso> cursoOptional = this.service.findOne(id);
		
		if (!cursoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoDB = cursoOptional.get();
		
		
		alumnos.forEach(alumno -> {
			cursoDB.addAlumno(alumno);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
		
	}
	
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable long id){
		Optional<Curso> cursoOptional = this.service.findOne(id);
		
		if (!cursoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoDB = cursoOptional.get();
		
		
		cursoDB.removeAlumno(alumno);
	
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
		
	}
	
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnoId(id);
		return ResponseEntity.ok(curso);
	}
	
}
