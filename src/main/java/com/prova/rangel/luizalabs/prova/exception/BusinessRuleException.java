package com.prova.rangel.luizalabs.prova.exception;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class BusinessRuleException extends RuntimeException{
	
	private List<String> messageList;
	
	public BusinessRuleException(Throwable cause) {
        this(cause, new ArrayList<>());
    }

    public BusinessRuleException(List<String> messageList) {
        this(null, messageList);
    }

    public BusinessRuleException(String message){
        this(null, Arrays.asList(message));
    }
    
    private BusinessRuleException(Throwable cause, List<String> messageList) {
        super(String.join("\n", messageList), cause);
        this.messageList = messageList;
    }
	
}
