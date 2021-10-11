package br.com.hdi.apijava.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4594673040901221495L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
