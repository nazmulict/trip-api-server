package com.nazmul.trip.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Error Message for Rest Api
 *
 * @author Nazmul
 * @version 1.0.0
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {
	private int statusCode;
	private String message;

	public ErrorInfo(String message)
	{
		super();
		this.message = message;
	}
}
