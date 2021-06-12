package com.algaworks.osworks.domain.exception;

/**
 *
 * @author Eraldo Lima
 *
 */
public class EntidadeNaoEncotradaException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncotradaException(String message) {
		super(message);
	}
}
