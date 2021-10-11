package br.com.hdi.apijava.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ExternalRepositoryException extends RuntimeException {
	private static final long serialVersionUID = -6296715730041539868L;

	@Getter
	private int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
	
	public ExternalRepositoryException(String message) {
		super(message);
	}

	public ExternalRepositoryException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ExternalRepositoryException(String message, int statusCode, Throwable cause) {
		super(message, cause);
		this.statusCode = statusCode;
	}
}
