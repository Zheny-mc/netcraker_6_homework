package ru.netcraker.extraEntityForRequest.task5;

import jdk.jfr.Label;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistoryPurchaseBook {
	private String title;
	private String warehouse;
	private Integer volumePurchaiseBook;
	private Integer cost;
}
