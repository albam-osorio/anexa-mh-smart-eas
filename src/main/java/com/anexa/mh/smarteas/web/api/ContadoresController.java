package com.anexa.mh.smarteas.web.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anexa.mh.smarteas.constants.SmartEasRestConstants;
import com.anexa.mh.smarteas.dto.ContadorDto;
import com.anexa.mh.smarteas.service.api.ContadorService;

import lombok.val;

@RestController
@RequestMapping(value = SmartEasRestConstants.contadores, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ContadoresController {

	@Autowired
	private ContadorService service;

	@GetMapping(params = { "fecha" })
	public ResponseEntity<List<ContadorDto>> getByFecha(
			@RequestParam @DateTimeFormat(pattern = SmartEasRestConstants.dateFormat) LocalDate fecha) {

		val result = service.findAllByFecha(fecha);

		return ResponseEntity.ok(result);
	}
	
	@GetMapping(params = { "fecha-hora" })
	public ResponseEntity<List<ContadorDto>> getNovedades(
			@RequestParam("fecha-hora") @DateTimeFormat(pattern = SmartEasRestConstants.dateTimeFormat) LocalDateTime fechaHora) {

		val result = service.findAllByFechaHora(fechaHora);

		return ResponseEntity.ok(result);
	}

}
