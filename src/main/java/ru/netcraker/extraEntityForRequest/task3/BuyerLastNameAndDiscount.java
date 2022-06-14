package ru.netcraker.extraEntityForRequest.task3;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BuyerLastNameAndDiscount {
	private String lastName;
	private Integer discount;
}
