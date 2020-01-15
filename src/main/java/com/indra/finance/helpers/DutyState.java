package com.indra.finance.helpers;

public enum DutyState {

	RC001("PENDIENTE"), RC002("PAGADO");

	private String code;

	private DutyState(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
