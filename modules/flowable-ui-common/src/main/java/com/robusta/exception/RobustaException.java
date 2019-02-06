package com.robusta.exception;

public class RobustaException extends RuntimeException {

	public RobustaException() {
		super();
	}

	public RobustaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RobustaException(String message, Throwable cause) {
		super(message, cause);
	}

	public RobustaException(String message) {
		super(message);
	}

	public RobustaException(Throwable cause) {
		super(cause);
	}

}
