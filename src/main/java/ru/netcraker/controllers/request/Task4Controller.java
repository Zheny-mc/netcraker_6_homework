package ru.netcraker.controllers.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netcraker.extraEntityForRequest.task4.PurchaseBuyerLastNameAndShopTitle;
import ru.netcraker.extraEntityForRequest.task4.PurchaseFull;
import ru.netcraker.service.Task4Service;

import java.util.List;

@RestController
@RequestMapping("/api/task4")
public class Task4Controller {
	@Autowired
	private Task4Service task4Service;

	@GetMapping("/a")
	private List<PurchaseBuyerLastNameAndShopTitle> findAllPurchaseBuyerLastNameAndShopTitle() {
		return task4Service.findAllPurchaseBuyerLastNameAndShopTitle();
	}

	@GetMapping("/b")
	public List<PurchaseFull> findAllPurchaseFull() {
		return task4Service.findAllPurchaseFull();
	}
}

