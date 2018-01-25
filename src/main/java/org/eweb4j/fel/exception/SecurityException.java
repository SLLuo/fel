package org.eweb4j.fel.exception;

@SuppressWarnings("serial")
public class SecurityException extends RuntimeException {
	public SecurityException(String msg) {
		super(msg);
	}

	public SecurityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
