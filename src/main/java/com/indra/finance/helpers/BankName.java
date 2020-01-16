package com.indra.finance.helpers;

public enum BankName {
	
	RC001("BANCO1"), RC002("RECAUDADORA1"), RC003("PASARELA1");

	private String code;

	private BankName(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
