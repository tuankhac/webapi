package com.neo.app.exceptions;

public class EzException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int code;
	private String message;

	public EzException() {
	}

	public EzException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {

		return code;
	}

	public String getMessage() {
		return message;

	}
}
