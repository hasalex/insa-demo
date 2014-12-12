package fr.sewatech.formation.appserv.service;


public class SewaException extends RuntimeException {

	public SewaException() {
		super();
	}

	public SewaException(String message, Throwable cause) {
		super(message, cause);
	}

	public SewaException(String message) {
		super(message);
	}

	public SewaException(Throwable cause) {
		super(cause);
	}

}
