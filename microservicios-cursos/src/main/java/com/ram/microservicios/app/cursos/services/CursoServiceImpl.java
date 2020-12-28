package com.ram.microservicios.app.cursos.services;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ram.microservicios.app.cursos.models.entity.Curso;
import com.ram.microservicios.app.cursos.models.repository.ICursoRepository;
import com.ram.microservicios.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, ICursoRepository> implements ICursoService {

	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		
		return repository.findCursoByAlumnoId(id);
	}



}
