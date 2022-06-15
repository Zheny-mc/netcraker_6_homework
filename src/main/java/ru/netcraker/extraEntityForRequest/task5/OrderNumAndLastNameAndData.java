package ru.netcraker.extraEntityForRequest.task5;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;

@Data
@AllArgsConstructor
public class OrderNumAndLastNameAndData {
	private Long orderNumber;
	private String buyerLastName;
	private Calendar dataTime;
}
