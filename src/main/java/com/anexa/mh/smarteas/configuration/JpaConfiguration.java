package com.anexa.mh.smarteas.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;

import lombok.val;

@Configuration
@EnableTransactionManagement
public class JpaConfiguration extends HikariConfig {

	@Autowired
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
		val result = new NamedParameterJdbcTemplate(dataSource);
		return result;
	}
}