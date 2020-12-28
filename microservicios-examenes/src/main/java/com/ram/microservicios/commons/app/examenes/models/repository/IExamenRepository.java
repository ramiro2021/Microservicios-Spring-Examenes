package com.ram.microservicios.commons.app.examenes.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.ram.microservicios.commons.app.examenes.models.entity.Examen;

public interface IExamenRepository extends CrudRepository<Examen, Long> {

}
