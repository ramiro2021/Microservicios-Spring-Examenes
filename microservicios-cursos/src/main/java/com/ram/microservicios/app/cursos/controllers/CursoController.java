package com.ram.microservicios.app.cursos.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ram.microservicios.app.cursos.models.entity.Curso;
import com.ram.microservicios.app.cursos.services.ICursoService;
import com.ram.microservicios.commons.alumnos.models.entity.Alumno;
import com.ram.microservicios.commons.controllers.CommonController;
import com.ram.microservicios.commons.examenes.models.entity.Examen;

@RestController
public class CursoController extends CommonController<Curso, ICursoService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) {
			return this.validar(result);
		}
		Optional<Curso> cursoOptional = this.service.findOne(id);

		if (!cursoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Curso cursoDB = cursoOptional.get();

		cursoDB.setNombre(curso.getNombre());

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));
	}

	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable long id) {
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
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable long id) {
		Optional<Curso> cursoOptional = this.service.findOne(id);

		if (!cursoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Curso cursoDB = cursoOptional.get();

		cursoDB.removeAlumno(alumno);

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));

	}

	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id) {
		Curso curso = service.findCursoByAlumnoId(id);

		if (curso != null) {
			List<Long> examenesId = (List<Long>) service.obtenerExamenesIdsConRespuestasAlumno(id);

			List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
				if (examenesId.contains(examen.getId())) {
					examen.setRespondido(true);
				}
				return examen;
			}).collect(Collectors.toList());
			
			curso.setExamenes(examenes);
		}

		return ResponseEntity.ok(curso);
	}

	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamen(@RequestBody List<Examen> examenes, @PathVariable long id) {
		Optional<Curso> cursoOptional = this.service.findOne(id);

		if (!cursoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Curso cursoDB = cursoOptional.get();

		examenes.forEach(e -> {
			cursoDB.addExamen(e);
		});

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));

	}

	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable long id) {
		Optional<Curso> cursoOptional = this.service.findOne(id);

		if (!cursoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Curso cursoDB = cursoOptional.get();

		cursoDB.removeExamen(examen);

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoDB));

	}

}
