package com.anexa.mh.smarteas.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.anexa.mh.smarteas.dto.ContadorDto;
import com.anexa.mh.smarteas.service.api.ContadorService;

import lombok.val;

@Service
public class ContadorServiceImpl implements ContadorService {

	@Autowired
	protected NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<ContadorDto> findAllByFecha(LocalDate fecha) {
		val desde = fecha.atStartOfDay();
		val hasta = desde.plusDays(1L);
		val result = findAllByFechaHoraDesdeHasta(desde, hasta);
		return result;
	}

	@Override
	public List<ContadorDto> findAllByFechaHora(LocalDateTime fechaHora) {
		val desde = fechaHora.truncatedTo(ChronoUnit.HOURS);
		val hasta = desde.plusHours(1L);
		val result = findAllByFechaHoraDesdeHasta(desde, hasta);
		return result;
	}

	protected List<ContadorDto> findAllByFechaHoraDesdeHasta(LocalDateTime desde, LocalDateTime hasta) {
		val sql = getSelect();

		val parametros = new MapSqlParameterSource();
		parametros.addValue("desde", desde);
		parametros.addValue("hasta", hasta);

		val result = jdbcTemplate.query(sql, parametros, getRowMapper());

		return result;
	}

	private String getSelect() {
		// @formatter:off
		val result = ""
			+ "SELECT\n" + 
			"     a.[Año] AS ano\n" + 
			"    ,a.Mes AS mes\n" + 
			"    ,a.Dia AS dia\n" + 
			"    ,a.idStore\n" + 
			"    ,a.intervalDateTime\n" + 
			"    ,CAST(a.inCount AS INT) AS inCount\n" + 
			"FROM dbo.MH_CONTADORES a\n" + 
			"WHERE\n" + 
			"    a.intervalDateTime >= :desde\n" + 
			"AND a.intervalDateTime <  :hasta\n" +
			"";
		// @formatter:on
		return result;
	}

	private RowMapper<ContadorDto> getRowMapper() {
		return (rs, rowNum) -> {
			// @formatter:off
			val result = ContadorDto
					.builder()
					.ano(rs.getInt("ano"))
					.mes(rs.getInt("mes"))
					.dia(rs.getInt("dia"))
					.idStore(rs.getString("idStore").trim())
					.intervalDateTime(rs.getTimestamp("intervalDateTime").toLocalDateTime())
					.inCount(rs.getInt("inCount"))
					.build();
			// @formatter:on
			return result;
		};
	}

}
