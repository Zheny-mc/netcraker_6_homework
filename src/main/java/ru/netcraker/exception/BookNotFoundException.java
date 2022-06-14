package ru.netcraker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class BookNotFoundException extends Exception {
	public BookNotFoundException(String message) {
		super(message);
	}
}
