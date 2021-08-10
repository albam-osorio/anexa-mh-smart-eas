package com.anexa.mh.smarteas.service.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.anexa.mh.smarteas.dto.ContadorDto;

@Transactional(readOnly = true)
public interface ContadorService {

	List<ContadorDto> findAllByFecha(LocalDate fecha);
	
	List<ContadorDto> findAllByFechaHora(LocalDateTime fechaHora);
}
