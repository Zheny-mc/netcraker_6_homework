package ru.netcraker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcraker.extraEntityForRequest.task2.BookTitleAndCost;
import ru.netcraker.extraEntityForRequest.task2.BuyerAreaOfResidence;
import ru.netcraker.extraEntityForRequest.task2.PurchaseMonth;
import ru.netcraker.repository.BookRepository;
import ru.netcraker.repository.BuyerRepository;
import ru.netcraker.repository.PurchaseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Task2Service {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BuyerRepository buyerRepository;

	@Autowired
	private PurchaseRepository purchaseRepository;

	public List<BookTitleAndCost> findAllTitleAndCost() {
		return bookRepository.findAll().stream()
				.map(i -> new BookTitleAndCost(i.getTitle(), i.getCost()))
				.collect(Collectors.toList());
	}

	public List<BuyerAreaOfResidence> findAllAreaOfResidence() {
		return buyerRepository.findAll().stream()
				.map(i -> new BuyerAreaOfResidence(i.getAreaOfResidence()))
				.collect(Collectors.toList());
	}

	public List<PurchaseMonth> findAllMonth() {
		return purchaseRepository.findAll().stream()
				.map(i -> new PurchaseMonth(i.getCalendar().getTime()
						.toString().split(" ")[1]))
				.collect(Collectors.toList());
	}


}
