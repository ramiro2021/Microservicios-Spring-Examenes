package com.ram.microservicios.app.cursos.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ram.microservicios.app.cursos.clients.IRespuestaFeignClient;
import com.ram.microservicios.app.cursos.models.entity.Curso;
import com.ram.microservicios.app.cursos.models.repository.ICursoRepository;
import com.ram.microservicios.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, ICursoRepository> implements ICursoService {

	@Autowired
	private IRespuestaFeignClient client;
	
	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
		
		return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
	}



}
