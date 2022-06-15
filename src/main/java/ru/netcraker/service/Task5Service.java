package ru.netcraker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcraker.entity.Book;
import ru.netcraker.entity.Purchase;
import ru.netcraker.entity.Shop;
import ru.netcraker.extraEntityForRequest.task4.PurchaseBuyerLastNameAndShopTitle;
import ru.netcraker.extraEntityForRequest.task4.PurchaseFull;
import ru.netcraker.extraEntityForRequest.task5.HistoryPurchaseBook;
import ru.netcraker.extraEntityForRequest.task5.LastNameAndLocationAreaAndData;
import ru.netcraker.extraEntityForRequest.task5.OrderNumAndLastNameAndData;
import ru.netcraker.repository.PurchaseRepository;
import ru.netcraker.repository.ShopRepository;

import javax.transaction.Transactional;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Task5Service {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private ShopRepository shopRepository;

	@Transactional
	public List<OrderNumAndLastNameAndData> findAllOrderNumAndLastNameAndData(Integer summa) {
		List<Purchase> purchaseList = purchaseRepository.findAllBySummaGreaterThanEqual(summa);
		List<OrderNumAndLastNameAndData> resList = new ArrayList<>();

		purchaseList.forEach(
				pur -> pur.getBuyerList()
						.forEach(buyer -> resList.add(
								new OrderNumAndLastNameAndData(
										pur.getOrderNumber(),
										buyer.getLastName(),
										pur.getCalendar())
								)
						)
		);

		return resList;
	}

	@Transactional
	public List<LastNameAndLocationAreaAndData> findAllLastNameAndLocationAreaAndData(String month) {
		List<Purchase> purchaseList = purchaseRepository.findAllByOrderByOrderNumberAsc();
		List<LastNameAndLocationAreaAndData> resList = new ArrayList<>();

		for (var pur: purchaseList) {
			for (var buyer: pur.getBuyerList()) {
				for (var shop: pur.getShopList()) {
					resList.add(
						new LastNameAndLocationAreaAndData(
							buyer.getLastName(),
							shop.getLocationArea(),
							pur.getCalendar()
						)
					);
				}
			}
		}

		return resList;
	}

	@Transactional
	public List<Shop> findAllByCommissionBetweenAndLocationAreaNotContaining(
			Integer commission, Integer commission2, String locationArea) {

		List<Shop> shopList = new ArrayList<>();
		List<Purchase> purchaseList = purchaseRepository.findAll();
		for (var i: purchaseList) {
			shopList.addAll(i.getShopList());
		}

		List<Shop> sourceShop = shopRepository.findAllByCommissionBetweenAndLocationAreaNotContaining(
				commission, commission2, locationArea);

		//пересечение двух списков будет результат
		List<Shop> resList = new ArrayList<>();
		sourceShop.forEach(i -> {
			if (shopList.contains(i))
				resList.add(i);
		});

		return resList;
	}

	@Transactional
	public List<HistoryPurchaseBook> findAllHistoryRurchaiseBook(Integer currentVolume) {

		return purchaseRepository.findAll().stream()
				.filter(i -> i.getBookList().get(0).getVolume() > currentVolume)
				.map(pur -> {
					Book book = pur.getBookList().get(0);
					return new HistoryPurchaseBook(
							book.getTitle(),
							book.getWarehouse(),
							pur.getVolume(),
							book.getCost()
					);
				}).collect(Collectors.toList());
	}

}
