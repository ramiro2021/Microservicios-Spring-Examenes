package com.ram.microservicios.commons.services;

import java.util.Optional;



public interface ICommonService <E> {
	
	
	public Iterable<E> findAll();
	public Optional<E> findOne(long id);
	public E save(E entity);
	public void deleteById(Long id);
	

}
