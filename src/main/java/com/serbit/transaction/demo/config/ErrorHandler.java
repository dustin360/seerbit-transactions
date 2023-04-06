package com.serbit.transaction.demo.config;


import com.fasterxml.jackson.core.JsonParseException;
import com.serbit.transaction.demo.dto.GeneralResponse;
import com.serbit.transaction.demo.exception.BadRequestException;
import com.serbit.transaction.demo.exception.ConflictException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class ErrorHandler {



	// Overide because this message is created elsewhere
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public GeneralResponse handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex) {
		log.error(ex.getMessage(), ex);

		log.info("Method Argument not valid throwing....");

		return null;

	}


	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse illegalArgumentExceptionHandler(IllegalArgumentException ex) {
		log.error(ex.getMessage(), ex);
		return new GeneralResponse(HttpStatus.BAD_REQUEST.value(),
				ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { ConflictException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public GeneralResponse conflictExceptionHandler(ConflictException ex) {
		log.error(ex.getMessage(), ex);
		return new GeneralResponse(HttpStatus.CONFLICT.value(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { HttpMediaTypeNotSupportedException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse httpMediaTypeNotSupportedHandler(HttpMediaTypeNotSupportedException ex) {
		log.error(ex.getMessage(), ex);
		return null;
	}


	@ExceptionHandler(value = { DateTimeParseException.class })
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public GeneralResponse dateTimeParseExceptionHandler(DateTimeParseException ex) {
		log.error(ex.getMessage(), ex);
		return null;
	}

	@ExceptionHandler(value = { DateTimeException.class })
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public GeneralResponse dateTimeExceptionHandler(DateTimeException ex) {
		log.error(ex.getMessage(), ex);
		return null;
	}

	@ExceptionHandler(value = { BadRequestException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse badRequestExceptionHandler(BadRequestException ex) {
		return new GeneralResponse(HttpStatus.BAD_REQUEST.value(),
				ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	public ResponseEntity httpMessageNotReadableException(HttpMessageNotReadableException ex) {

		if (Strings.isNotBlank(ex.getMessage()) && ex.getMessage().contains("JsonParseException"))
			return ResponseEntity.badRequest().body(null);

		else
			return ResponseEntity.unprocessableEntity().body(null);

	}

	@ExceptionHandler(value = { JsonParseException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public GeneralResponse jsonParseExceptionHandler(JsonParseException ex) {
		log.error(ex.getMessage(), ex);
		return null;
	}



	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public GeneralResponse handleGeneralExceptions(Exception ex,
                                                   HttpServletRequest request, HttpServletResponse response) {

		log.error(ex.getMessage(), ex);

		return new GeneralResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unfortunately, the request cannot be processed");
	}

}
