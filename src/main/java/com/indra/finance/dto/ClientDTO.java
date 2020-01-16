package com.indra.finance.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

	@NotNull
	private String identification;
	@NotNull
	private String documentType;
	private String clientName;
	private String clientMiddleName;
	private String clientFirtLastName;
	private String clientSecondLastName;
	private String businessName;
	@NotNull
	private String address;
	@NotNull
	private Long phone;

}
