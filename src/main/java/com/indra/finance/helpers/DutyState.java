package com.indra.finance.helpers;

public enum DutyState {

	PENDIENTE("RC-001"), PAGADO("RC-002");

	private String code;

	private DutyState(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
