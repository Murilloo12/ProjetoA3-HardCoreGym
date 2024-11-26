package br.com.murillo.hardcoregym.application.util;

import javax.ejb.ApplicationException;

@ApplicationException
public class ValidationException extends RuntimeException {
	
	public ValidationException(String message) {
		super(message);
	}
	
}
