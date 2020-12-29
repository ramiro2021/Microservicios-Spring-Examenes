package com.ram.microservicios.commons.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ICommonService <E> {
	
	public Page<E> findAll(Pageable page);
	public Iterable<E> findAll();
	public Optional<E> findOne(long id);
	public E save(E entity);
	public void deleteById(Long id);
	

}
