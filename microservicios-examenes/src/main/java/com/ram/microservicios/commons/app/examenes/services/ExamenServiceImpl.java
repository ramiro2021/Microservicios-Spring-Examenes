package com.ram.microservicios.commons.app.examenes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ram.microservicios.commons.app.examenes.models.repository.IAsignaturaRepository;
import com.ram.microservicios.commons.app.examenes.models.repository.IExamenRepository;
import com.ram.microservicios.commons.examenes.models.entity.Asignatura;
import com.ram.microservicios.commons.examenes.models.entity.Examen;
import com.ram.microservicios.commons.services.CommonServiceImpl;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, IExamenRepository> implements IExamenService {

	@Autowired
	private IAsignaturaRepository asignaturaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		
		return repository.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Asignatura> findAllasignaturas() {
		
		return asignaturaRepository.findAll();
	}



}
