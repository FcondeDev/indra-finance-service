package com.indra.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DutyDTO {

	private Long dutyId;
	private String dutyName;
	private Long clientIdentification;
	private String dutyDescription;
	private Long totalValue;
	private Long currentPeriodValue;
	private Long numberOfPeriods;
	private Long currentPeriod;
	private String status;
}
