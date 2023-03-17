package com.prova.rangel.luizalabs.prova.domain.response;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class SimpleResponse {
	private List<String> body = new ArrayList<>();
	private HttpStatus httpStatus = HttpStatus.OK;
	
	public void setBody(String message) {
		this.body.add(message);
	}
	
	public SimpleResponse(HttpStatus httpStatus, String message) {
		this.body.add(message);
		if(httpStatus != null) {
			this.httpStatus = httpStatus;
		}
	}
}
