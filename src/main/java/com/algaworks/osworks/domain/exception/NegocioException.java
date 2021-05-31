package com.algaworks.osworks.domain.exception;

/**
 *
 * @author Eraldo Lima
 *
 */
public class NegocioException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NegocioException(String message) {
		super(message);				
	}

}
