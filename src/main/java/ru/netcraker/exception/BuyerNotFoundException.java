package ru.netcraker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class BuyerNotFoundException extends Exception {
	public BuyerNotFoundException(String message) {
		super(message);
	}
}
