package com.indra.selecaoJava.exception;

public class TimeoutExceptionAPI extends ExceptionDefault {

	private static final long serialVersionUID = -8868385103830687211L;

	public TimeoutExceptionAPI(String key, String msg) {
		super(key, msg);
	}
	
	public TimeoutExceptionAPI(ExceptionsMessagesEnum globalRegistroNaoEncontrado){
        super(globalRegistroNaoEncontrado);
   }
}
