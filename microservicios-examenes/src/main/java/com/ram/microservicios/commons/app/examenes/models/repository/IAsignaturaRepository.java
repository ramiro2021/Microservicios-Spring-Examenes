package com.ram.microservicios.commons.app.examenes.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.ram.microservicios.commons.examenes.models.entity.Asignatura;

public interface IAsignaturaRepository extends CrudRepository<Asignatura, Long> {

}
