package ru.netcraker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netcraker.entity.Purchase;
import ru.netcraker.extraEntityForRequest.task4.PurchaseBuyerLastNameAndShopTitle;
import ru.netcraker.extraEntityForRequest.task4.PurchaseFull;
import ru.netcraker.repository.PurchaseRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class Task4Service {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Transactional
	public List<PurchaseBuyerLastNameAndShopTitle> findAllPurchaseBuyerLastNameAndShopTitle() {
		List<Purchase> purchaseList =  purchaseRepository.findAll();
		List<PurchaseBuyerLastNameAndShopTitle> listLastNameAndTitle = new ArrayList<>();

		for (var pur: purchaseList) {
			for (var buyer: pur.getBuyerList()) {
				for (var shop: pur.getShopList()) {
					listLastNameAndTitle.add(
							new PurchaseBuyerLastNameAndShopTitle(
									buyer.getLastName(), shop.getTitle()));
				}
			}
		}

		return listLastNameAndTitle;
	}

	@Transactional
	public List<PurchaseFull> findAllPurchaseFull() {
		List<Purchase> purchaseList =  purchaseRepository.findAll();
		List<PurchaseFull> listLastNameAndTitle = new ArrayList<>();

		for (var pur: purchaseList) {
			for (var buyer: pur.getBuyerList()) {
				for (var book : pur.getBookList()) {
					listLastNameAndTitle.add(
							new PurchaseFull(
									pur.getCalendar(),
									buyer.getLastName(),
									buyer.getDiscount(),
									book.getTitle(),
									pur.getVolume())
					);
				}
			}
		}

		return listLastNameAndTitle;
	}



}
