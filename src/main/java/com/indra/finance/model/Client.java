package com.indra.finance.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "client")
public class Client implements Serializable {

	private static final long serialVersionUID = 4589235900157361094L;

	@Id
	private String identification;
	private String documentType;
	private String clientName;
	private String clientMiddleName;
	private String clientFirtLastName;
	private String clientSecondLastName;
	private String businessName;
	private String address;
	private Long phone;
	private boolean disable;

}
