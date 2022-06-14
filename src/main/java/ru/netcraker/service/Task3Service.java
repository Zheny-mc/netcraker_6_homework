package ru.netcraker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcraker.extraEntityForRequest.task2.BookTitleAndCost;
import ru.netcraker.extraEntityForRequest.task3.BuyerLastNameAndDiscount;
import ru.netcraker.extraEntityForRequest.task3.ShopTitle;
import ru.netcraker.repository.BookRepository;
import ru.netcraker.repository.BuyerRepository;
import ru.netcraker.repository.ShopRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Task3Service {

	@Autowired
	private BuyerRepository buyerRepository;

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private BookRepository bookRepository;

	public List<BuyerLastNameAndDiscount> findAllBuyerTitleAndCost(String areaOfResidence) {
		return buyerRepository.findAllByAreaOfResidence(areaOfResidence).stream()
				.map(i -> new BuyerLastNameAndDiscount(i.getLastName(), i.getDiscount()))
				.collect(Collectors.toList());
	}

	public List<ShopTitle> findAllShopTitle(List<String> locationAreaList) {
		List<ShopTitle> shopTitles = new ArrayList<>();

		for (var it: locationAreaList) {
			shopTitles.addAll(shopRepository.findAllByLocationArea(it)
					.stream().map(i -> new ShopTitle(i.getTitle()))
					.collect(Collectors.toList()));
		}
		return shopTitles;
	}

	public List<BookTitleAndCost> findByCostGreaterThanOrTitleStartingWith(Integer cost, String prefix) {
		List<BookTitleAndCost> bookTitleAndCosts =  bookRepository.findByCostGreaterThanOrTitleStartingWith(cost, prefix)
				.stream().map(i -> new BookTitleAndCost(i.getTitle(), i.getCost()))
				.collect(Collectors.toList());

		Collections.sort(bookTitleAndCosts, (o1, o2) -> o2.getCost().compareTo(o1.getCost()));
		return bookTitleAndCosts;
	}

}
