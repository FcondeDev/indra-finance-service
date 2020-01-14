package com.indra.finance.helpers;

public enum DocumentType {

	CC("TD-001"),CE("TD-002"),PP("TD-003"),NIT("TD-004");

	private String code;

	private DocumentType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	

}
