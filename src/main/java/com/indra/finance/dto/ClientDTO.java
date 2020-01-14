package com.indra.finance.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClientDTO {

	private Long identification;
	private String documentType;
	private String clientName;
	private String clientMiddleName;
	private String clientFirtLastName;
	private String clientSecondLastName;
	private String businessName;
	private String address;
	private Long phone;
	private List<DutyDTO> clientDuties;

}
