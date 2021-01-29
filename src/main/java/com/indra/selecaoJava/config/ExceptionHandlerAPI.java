package com.indra.selecaoJava.config;

import com.indra.selecaoJava.exception.BadRequestExceptionAPI;
import com.indra.selecaoJava.exception.ExceptionDefault;
import com.indra.selecaoJava.exception.ExceptionsMessagesEnum;
import com.indra.selecaoJava.exception.ServerErrorExceptionAPI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


@ControllerAdvice
@RestController
public class ExceptionHandlerAPI {

	     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	     @ExceptionHandler(ServerErrorExceptionAPI.class)
	     public @ResponseBody ErroInfo handleExceptionServerError(HttpServletResponse response, HttpServletRequest request, Exception exception) {

	          ErroInfo erroInfo = buildErrorInfo(request, exception);
	          return erroInfo;

	     }

	     @ResponseStatus(HttpStatus.BAD_REQUEST)
	     @ExceptionHandler(BadRequestExceptionAPI.class)
	     public @ResponseBody ErroInfo handleExceptionBadRequest(HttpServletResponse response, HttpServletRequest request, Exception exception) {

	          ErroInfo erroInfo = buildErrorInfo(request, exception);
	          return erroInfo;

	     }
	     
	     @ResponseStatus(HttpStatus.BAD_REQUEST)
	     @ExceptionHandler(MethodArgumentNotValidException.class)
	     public @ResponseBody ErroInfo handleExceptionMethodArgumentNotValid(HttpServletResponse response, HttpServletRequest request, MethodArgumentNotValidException exception) {

	    	 ErroInfo erroInfo = new ErroInfo(LocalDateTime.now(), 400, exception.getClass().getSimpleName(), exception.getBindingResult().getFieldError().getDefaultMessage(), request.getRequestURI());
	          
	         return erroInfo;

	     }
	     
	     @ResponseStatus(HttpStatus.BAD_REQUEST)
	     @ExceptionHandler(IllegalArgumentException.class)
	     public @ResponseBody ErroInfo handleExceptionIllegalArgument(HttpServletResponse response, HttpServletRequest request, Exception exception) {

	    	  ExceptionDefault exceptionAPI = new ExceptionDefault(ExceptionsMessagesEnum.GLOBAL_ERRO_BANCO);
	          
	          ErroInfo erroInfo = new ErroInfo(LocalDateTime.now(), exceptionAPI.getMsgEnum().getCodigo(), exceptionAPI.getClass().getSimpleName(), exceptionAPI.getMessage(), request.getRequestURI());
	          
	          return erroInfo;

	     }
	     
	     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	     @ExceptionHandler(Exception.class)
	     public @ResponseBody ErroInfo handleException(HttpServletResponse response, HttpServletRequest request, Exception exception) {
	          
	          ExceptionDefault exceptionAPI = new ExceptionDefault(ExceptionsMessagesEnum.GLOBAL_ERRO_SERVIDOR);
	          
	          ErroInfo erroInfo = new ErroInfo(LocalDateTime.now(), exceptionAPI.getMsgEnum().getCodigo(), exceptionAPI.getClass().getSimpleName(), exceptionAPI.getMessage(), request.getRequestURI());
	          
	          return erroInfo;
	          
	     }
	      
	     @ResponseStatus(HttpStatus.BAD_REQUEST)
	     @ExceptionHandler({
			HttpRequestMethodNotSupportedException.class,
			HttpMediaTypeNotSupportedException.class,
			HttpMediaTypeNotAcceptableException.class,
			MissingPathVariableException.class,
			MissingServletRequestParameterException.class,
			ServletRequestBindingException.class,
			ConversionNotSupportedException.class,
			TypeMismatchException.class,
			HttpMessageNotReadableException.class,
			HttpMessageNotWritableException.class,
			MissingServletRequestPartException.class,
			NoHandlerFoundException.class,
			AsyncRequestTimeoutException.class
	     })
	     public @ResponseBody ResponseEntity<Object> handleException(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
	          
	          return new ResponseEntity<Object>(body, headers, status);
	          
	     }
	     
	     /**
	      * Método responsável por criar o objeto que representa a exceção.
	      */
	     public ErroInfo buildErrorInfo(HttpServletRequest request, Exception exception) {

	    	 ExceptionDefault exceptionResponse;
	          
	          if(exception instanceof ExceptionDefault) {               
	               exceptionResponse = (ExceptionDefault) exception;
	          } else {
	               exceptionResponse = new ExceptionDefault(ExceptionsMessagesEnum.GLOBAL_ERRO_SERVIDOR);
	          }
	          
	          ErroInfo erroInfo = new ErroInfo(LocalDateTime.now(), exceptionResponse.getMsgEnum().getCodigo(), exceptionResponse.getClass().getSimpleName(), exceptionResponse.getMessage(), request.getRequestURI());
	          return erroInfo;
	          
	     }

	     /**
	      * Classe que representa o objeto de retorno das exceções lançadas.
	      */
	     @AllArgsConstructor
	     @Getter
	     public class ErroInfo{

	          /**
	           * Timestamp do momento em que a exceção foi lançada.
	           */
	          @DateTimeFormat(iso = ISO.DATE_TIME)
	          public LocalDateTime timestamp;

	          /**
	           * Código identificador do tipo de exceção;S
	           */
	          public Integer code;

	          /**
	           * Nome da classe de exceção.
	           */
	          public String exception;

	          /**
	           * Mensagem com a descrição da exceção.
	           */
	          public String message;

	          /**
	           * Path que realizou a solicitação que causou a exceção.
	           */
	          public String path;

	     }

}
