package com.indra.selecaoJava.exception;

public class UnauthorizedExceptionAPI extends ExceptionDefault {

	private static final long serialVersionUID = -900018038377128551L;

	public UnauthorizedExceptionAPI(String key, String msg) {
		super(key, msg);
	}
	
	public UnauthorizedExceptionAPI(ExceptionsMessagesEnum globalRegistroNaoEncontrado){
        super(globalRegistroNaoEncontrado);
   }
}