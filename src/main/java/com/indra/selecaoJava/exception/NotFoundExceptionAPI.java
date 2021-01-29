package com.indra.selecaoJava.exception;

public class NotFoundExceptionAPI extends ExceptionDefault {

	private static final long serialVersionUID = -8331284501711209424L;

	public NotFoundExceptionAPI(String key, String msg) {
		super(key, msg);
	}
	
	public NotFoundExceptionAPI(ExceptionsMessagesEnum globalRegistroNaoEncontrado){
        super(globalRegistroNaoEncontrado);
   }
}
