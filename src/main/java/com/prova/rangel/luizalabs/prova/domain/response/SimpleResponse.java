package com.prova.rangel.luizalabs.prova.domain.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class SimpleResponse {
	private List<String> body = new ArrayList<>();
	private HttpStatus httpStatus = HttpStatus.OK;
	
	public SimpleResponse(HttpStatus httpStatus, String message) {
		this.body.add(message);
		if(httpStatus != null) {
			this.httpStatus = httpStatus;
		}
	}
	
	public List<String> getBody() {
		return body;
	}

	public void setBody(List<String> body) {
		this.body = body;
	}

	public void setBody(String message) {
		this.body.add(message);
	}
	
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
