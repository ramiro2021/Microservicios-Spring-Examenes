package com.ram.microservicios.app.usuarios.services;



import java.util.List;

import com.ram.microservicios.commons.alumnos.models.entity.Alumno;
import com.ram.microservicios.commons.services.ICommonService;

public interface IAlumnoService extends ICommonService<Alumno> {
	
	public List<Alumno> findByNombreOrApellido(String term);
}
