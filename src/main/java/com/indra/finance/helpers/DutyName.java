package com.indra.finance.helpers;

public enum DutyName {

	PR001("Tarjeta de Crédito VISA"), PR002("Tarjeta de Crédito MASTER CARD"), PR003("Crédito libre inversión"),
	PR004("Crédito Educativo"), PR005("Crédito de libranza");

	private String code;

	private DutyName(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
