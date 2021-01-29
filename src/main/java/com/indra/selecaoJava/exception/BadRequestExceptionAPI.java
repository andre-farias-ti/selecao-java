package com.indra.selecaoJava.exception;

public class BadRequestExceptionAPI extends ExceptionDefault {

	private static final long serialVersionUID = -1017302180621049875L;

	public BadRequestExceptionAPI(String key, String msg) {
		super(key, msg);
	}
	
	public BadRequestExceptionAPI(ExceptionsMessagesEnum globalRegistroNaoEncontrado){
        super(globalRegistroNaoEncontrado);
   }
}
