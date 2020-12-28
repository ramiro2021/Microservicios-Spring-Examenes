package com.ram.microservicios.commons.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.ram.microservicios.commons.services.ICommonService;


public class CommonController <E, S extends ICommonService<E>> {
	
	@Autowired
	protected S service;
	
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable long id) {
		
		Optional<E> alumnoOptional = service.findOne(id);
		if (alumnoOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(alumnoOptional.get());
		
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody E entity){
		
		E entityDB = service.save(entity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
	}
	
		
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
