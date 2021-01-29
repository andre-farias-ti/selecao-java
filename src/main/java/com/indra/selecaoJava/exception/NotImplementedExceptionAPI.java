package com.indra.selecaoJava.exception;

public class NotImplementedExceptionAPI extends ExceptionDefault {

	private static final long serialVersionUID = 5670715918325994035L;

	public NotImplementedExceptionAPI(String key, String msg) {
		super(key, msg);
	}
	
	public NotImplementedExceptionAPI(ExceptionsMessagesEnum globalRegistroNaoEncontrado){
        super(globalRegistroNaoEncontrado);
   }
}
