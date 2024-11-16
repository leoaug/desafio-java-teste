package br.com.desafiojava.exception;

public class DesafioJavaException extends Exception {

	private static final long serialVersionUID = 1L;

	public DesafioJavaException(String msg) {
		super(msg);
	}

	public DesafioJavaException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
