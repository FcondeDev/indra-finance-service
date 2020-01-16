package com.indra.finance.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "payment")
public class Payment implements Serializable {

	private static final long serialVersionUID = 3738133540804263633L;

	@Id
	private String paymentId;
	private String clientIdentification;
	private String dutyId;
	private String dutyName;
	private String bankName;
	private Date paymenteDate;
	private Long paymentValue;
	private Long paymentPeriod;

}
