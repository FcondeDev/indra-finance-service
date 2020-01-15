package com.indra.finance.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "duty")
public class Duty implements Serializable {

	private static final long serialVersionUID = 700192100413875989L;

	@Id
	private String dutyId;
	private String clientIdentification;
	private String dutyName;
	private String dutyDescription;
	private Long totalValue;
	private Long currentPeriodValue;
	private Long numberOfPeriods;
	private Long currentPeriod;
	private String status;
	private boolean disable;

}
