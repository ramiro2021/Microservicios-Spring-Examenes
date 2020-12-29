package com.ram.microservicios.commons.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
	
	@GetMapping("/pagina")
	public ResponseEntity<?> listar(Pageable page){
		return ResponseEntity.ok().body(service.findAll(page));
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
	public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result){
		
		if (result.hasErrors()) {
			return this.validar(result);
		}
		
		E entityDB = service.save(entity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
	}
	
		
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	protected ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores =new HashMap<>();
		
		result.getAllErrors().forEach(err -> {
			errores.put(err.getObjectName(), err.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errores);
		
	}
	
}
