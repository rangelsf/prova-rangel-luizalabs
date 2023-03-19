package com.prova.rangel.luizalabs.prova.config;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.prova.rangel.luizalabs.prova.domain.response.SimpleResponse;
import com.prova.rangel.luizalabs.prova.exception.*;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{

	static final Logger log = LoggerFactory.getLogger(ResponseExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<SimpleResponse> defaultHandler(Exception ex, WebRequest request) {
		Optional<ResponseStatus> responseStatus = getResponseStatus(ex.getClass());
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		try {
			if (responseStatus.isPresent()) {
				httpStatus = responseStatus.get().value();
				log.error("Error: {} , Status: {} ",ex, httpStatus);
			} else {
				log.error("Error: {} ", ex);
			}
		} catch (Exception e) {
			log.error("Exception: {} ", e);
		}
		return new ResponseEntity<>(new SimpleResponse(httpStatus, getMessage(ex)), httpStatus);
	}


	@ExceptionHandler(BusinessRuleException.class)
	public final ResponseEntity<SimpleResponse> handleBusinessExceptions(BusinessRuleException exception, WebRequest request) {
		Optional<ResponseStatus> responseStatus = getResponseStatus(exception.getClass());

		ResponseEntity<SimpleResponse> defaultResponseResponseEntity = createSimpleResponse(exception, responseStatus, HttpStatus.NOT_FOUND);

		if (defaultResponseResponseEntity.getBody() != null) {
			defaultResponseResponseEntity.getBody().setBody(exception.getMessage());
		}
		return defaultResponseResponseEntity;
	}


	private ResponseEntity<SimpleResponse> createSimpleResponse(Exception ex, Optional<ResponseStatus> annotationResponse, HttpStatus httpStatus) {
		try {
			log.error(ex.getMessage(), ex);
			if (annotationResponse.isPresent()) {
				httpStatus = annotationResponse.get().value();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return new ResponseEntity<>(new SimpleResponse(httpStatus, getMessage(ex)), httpStatus);
	}

	private Optional<ResponseStatus> getResponseStatus(Class<?> exceptionClass) {
		return Optional.ofNullable(AnnotationUtils.findAnnotation(exceptionClass, ResponseStatus.class));
	}


	private String getMessage(Exception ex) {
		return StringUtils.isEmpty(ex.getMessage()) ? ex.getClass().getSimpleName() : ex.getMessage();
	}

}
