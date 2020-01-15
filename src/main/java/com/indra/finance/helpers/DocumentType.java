package com.indra.finance.helpers;

public enum DocumentType {

	TD001("CC"), TD002("CE"), TD003("PP"), TD004("NIT");

	private String code;

	private DocumentType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
