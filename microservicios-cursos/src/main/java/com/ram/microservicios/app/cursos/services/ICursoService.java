package com.ram.microservicios.app.cursos.services;



import com.ram.microservicios.app.cursos.models.entity.Curso;
import com.ram.microservicios.commons.services.ICommonService;

public interface ICursoService extends ICommonService<Curso> {
	
	public Curso findCursoByAlumnoId(Long id);
	
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
}
