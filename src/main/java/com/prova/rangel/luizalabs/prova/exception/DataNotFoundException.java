package com.prova.rangel.luizalabs.prova.exception;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends BusinessRuleException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3553438543952562930L;

	public DataNotFoundException(String message) {
		super(message);
	}
	
	
	public DataNotFoundException(List<String> messageList) {
		super(messageList);
	}
	

}
