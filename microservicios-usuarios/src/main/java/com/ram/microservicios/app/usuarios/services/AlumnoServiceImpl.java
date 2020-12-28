package com.ram.microservicios.app.usuarios.services;




import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ram.microservicios.app.usuarios.models.repository.AlumnoRepository;
import com.ram.microservicios.commons.alumnos.models.entity.Alumno;
import com.ram.microservicios.commons.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements IAlumnoService {

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String term) {
		repository.findByNombreOrApellido(term);
		return repository.findByNombreOrApellido(term);
	}
	


}
