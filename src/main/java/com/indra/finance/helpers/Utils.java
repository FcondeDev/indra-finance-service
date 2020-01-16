package com.indra.finance.helpers;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	public String removeHyphen(String code) {
		return code.replace("-", "");
	}

	public String dutyWasPaid(Long payment, Long totalPayment) {

		if (totalPayment - payment == 0) {
			return DutyState.valueOf("RC002").getCode();
		} else {
			return DutyState.valueOf("RC001").getCode();
		}

	}

}
