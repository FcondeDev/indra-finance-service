package com.indra.finance.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {

	private String paymentId;
	@NotNull
	private String clientIdentification;
	@NotNull
	private String dutyId;
	@NotNull
	private String bankName;
	private Date paymenteDate;
	@NotNull
	private Long paymentValue;
	@NotNull
	private Long paymentPeriod;

}
