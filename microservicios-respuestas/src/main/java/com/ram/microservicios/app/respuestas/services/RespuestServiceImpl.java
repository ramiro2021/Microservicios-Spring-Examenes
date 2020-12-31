package com.ram.microservicios.app.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ram.microservicios.app.respuestas.models.entity.Respuesta;
import com.ram.microservicios.app.respuestas.models.repository.IRespuestaRepository;

@Service
public class RespuestServiceImpl implements IRespuestaService {

	@Autowired
	private IRespuestaRepository respuestaRepository;
	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		
		return respuestaRepository.saveAll(respuestas);
	}
	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		
		return respuestaRepository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		
		return respuestaRepository.findExamenesIdsConRespuestasByAlumno(alumnoId);
	}

}
