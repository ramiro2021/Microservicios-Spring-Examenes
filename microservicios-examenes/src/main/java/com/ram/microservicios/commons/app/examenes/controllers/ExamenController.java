package com.ram.microservicios.commons.app.examenes.controllers;


import java.util.ArrayList;
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

import com.ram.microservicios.commons.app.examenes.services.IExamenService;
import com.ram.microservicios.commons.controllers.CommonController;
import com.ram.microservicios.commons.examenes.models.entity.Examen;
import com.ram.microservicios.commons.examenes.models.entity.Pregunta;

@RestController
public class ExamenController extends CommonController<Examen, IExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id ) {
		
		if (result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Examen> examenOptional = service.findOne(id);
		if (!examenOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Examen examenDb = examenOptional.get();

		examenDb.setNombre(examen.getNombre());

		List <Pregunta> eliminadas = new ArrayList<>();
		// elimino las preguntas que el usuario haya decidido eliminar de la bd
		examenDb.getPreguntas().forEach(pdb->{
			if (!examen.getPreguntas().contains(pdb)) {
				eliminadas.add(pdb);
			}
		});
		
		eliminadas.forEach(p ->{
			examenDb.removePregunta(p);
		});
		
		
		// seteoo las preguntas
		examenDb.setPreguntas(examen.getPreguntas());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		
		return ResponseEntity.ok(service.findByNombre(term));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas(){
		return ResponseEntity.ok(service.findAllasignaturas());
		
	}

}
