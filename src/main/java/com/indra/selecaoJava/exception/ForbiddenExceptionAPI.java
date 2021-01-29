package com.indra.selecaoJava.exception;

public class ForbiddenExceptionAPI extends ExceptionDefault {

	private static final long serialVersionUID = -5199932600210809440L;

	public ForbiddenExceptionAPI(String key, String msg) {
		super(key, msg);
	}
	
	public ForbiddenExceptionAPI(ExceptionsMessagesEnum globalRegistroNaoEncontrado){
        super(globalRegistroNaoEncontrado);
   }
}
