package ru.netcraker.extraEntityForRequest.task4;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
public class PurchaseFull {
	private Calendar dataTime;
	private String buyerLastName;
	private Integer discount;
	private String bookTitle;
	private Integer volume;

	public Date getDataTime() {
		return dataTime.getTime();
	}
}
