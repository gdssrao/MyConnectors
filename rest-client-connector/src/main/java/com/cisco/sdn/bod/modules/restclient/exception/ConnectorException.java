package com.cisco.sdn.bod.modules.restclient.exception;

public class ConnectorException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConnectorException() {
	}

	public ConnectorException(String message) {
		super(message);
	}

	public ConnectorException(Throwable cause) {
		super(cause);
	}

	public ConnectorException(String message, Throwable cause) {
		super(message, cause);
	}

}
