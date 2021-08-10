package com.anexa.mh.smarteas.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContadorDto {
	private int ano;
	private int mes;
	private int dia;

	@NotNull
	@Size(max = 25)
	private String idStore;
	
	@NotNull
	private LocalDateTime intervalDateTime;
	
	@NotNull
	private int inCount;
}
