package com.indra.finance.helpers;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	public String removeHyphen(String code) {
		return code.replace("-", "");
	}

}
