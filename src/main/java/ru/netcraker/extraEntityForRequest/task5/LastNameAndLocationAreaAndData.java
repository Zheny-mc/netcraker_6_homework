package ru.netcraker.extraEntityForRequest.task5;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;

@Data
@AllArgsConstructor
public class LastNameAndLocationAreaAndData {
	private String buyerLastName;
	private String locationArea;
	private Calendar dataTime;
}
