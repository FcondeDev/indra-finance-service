package com.indra.finance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceException extends Exception {

	private static final long serialVersionUID = 4750582361753270418L;
	private final Integer httpStatus;
	private final String message;

	public ServiceException(Integer httpStatus, Enum<?> message) {
		this.httpStatus = httpStatus;
		this.message = message.name();
	}

	public ServiceException(Integer httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

}