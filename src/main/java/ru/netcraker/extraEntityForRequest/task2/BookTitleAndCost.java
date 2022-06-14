package ru.netcraker.extraEntityForRequest.task2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookTitleAndCost {
	private String title;
	private Integer cost;
}