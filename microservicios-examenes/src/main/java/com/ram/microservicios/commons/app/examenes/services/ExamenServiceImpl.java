package com.ram.microservicios.commons.app.examenes.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ram.microservicios.commons.app.examenes.models.repository.IExamenRepository;
import com.ram.microservicios.commons.examenes.models.entity.Examen;
import com.ram.microservicios.commons.services.CommonServiceImpl;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, IExamenRepository> implements IExamenService {



}
