package com.ram.microservicios.commons.app.examenes.services;

import java.util.List;

import com.ram.microservicios.commons.examenes.models.entity.Asignatura;
import com.ram.microservicios.commons.examenes.models.entity.Examen;
import com.ram.microservicios.commons.services.ICommonService;

public interface IExamenService extends ICommonService<Examen> {
	public List<Examen> findByNombre(String term);
	
	public Iterable<Asignatura> findAllasignaturas();
}
