package com.indra.finance.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DutyDTO {

	private String dutyId;
	@NotNull
	private String dutyName;
	@NotNull
	private String clientIdentification;
	@NotNull
	private String dutyDescription;
	@NotNull
	private Long totalValue;
	@NotNull
	private Long currentPeriodValue;
	@NotNull
	private Long numberOfPeriods;
	@NotNull
	private Long currentPeriod;
	@NotNull
	private String status;
}
