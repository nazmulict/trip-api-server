package com.nazmul.trip.handler;

import java.sql.SQLException;


import com.nazmul.trip.common.CommonConstant;
import com.nazmul.trip.exception.DataNotFoundException;
import com.nazmul.trip.model.ErrorInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;


/**
 * Global Error Handler
 *
 * @author Nazmul
 * @version 1.0.0
 *
 */
@RestController
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({IllegalArgumentException.class, NoResourceFoundException.class})
	public ErrorInfo handleNotFoundException(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public ErrorInfo handleRunTimeException(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonConstant.ERROR_INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
	public ErrorInfo handleInvalidMethodException(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(HttpStatus.METHOD_NOT_ALLOWED.value(),
				req.getMethod() + " METHOD NOT VALID AT THIS URL: " + req.getRequestURL());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler( DataNotFoundException.class)
	public ErrorInfo handleDataNotFoundException(HttpServletRequest req, Exception ex) {
		return new ErrorInfo(HttpStatus.NOT_FOUND.value(), CommonConstant.DATA_NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(SQLException.class)
	public ErrorInfo handleSQLException(HttpServletRequest request, Exception ex) {
		return new ErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
	}

}