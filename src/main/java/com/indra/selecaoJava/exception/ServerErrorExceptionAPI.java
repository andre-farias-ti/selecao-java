package com.indra.selecaoJava.exception;

public class ServerErrorExceptionAPI extends ExceptionDefault{

	private static final long serialVersionUID = -1984602622024316323L;

	public ServerErrorExceptionAPI(String key, String msg) {
		super(key, msg);
	}
	
	public ServerErrorExceptionAPI(ExceptionsMessagesEnum globalRegistroNaoEncontrado){
        super(globalRegistroNaoEncontrado);
   }
}
