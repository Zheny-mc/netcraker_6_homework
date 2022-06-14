package ru.netcraker.controllers.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netcraker.extraEntityForRequest.task2.BookTitleAndCost;
import ru.netcraker.extraEntityForRequest.task3.BuyerLastNameAndDiscount;
import ru.netcraker.extraEntityForRequest.task3.ShopTitle;
import ru.netcraker.service.Task3Service;

import java.util.List;

@RestController
@RequestMapping("/api/task3")
public class Task3Controller {
	@Autowired
	private Task3Service task3Service;

	@GetMapping("/a/{areaOfResidence}")
	private List<BuyerLastNameAndDiscount> findAllTitleAndCost(@PathVariable String areaOfResidence) {
		return task3Service.findAllBuyerTitleAndCost(areaOfResidence);
	}

	@GetMapping("/b")
	public List<ShopTitle> findAllAreaOfResidence(
			@RequestParam String locAreaOne, @RequestParam String locAreaTwo) {
		return task3Service.findAllShopTitle(
				List.of(locAreaOne, locAreaTwo)
		);
	}

	@GetMapping("/c")
	public List<BookTitleAndCost> findByCostGreaterThanOrTitleStartingWith(
			@RequestParam Integer cost, @RequestParam String prefix
	) {
		return task3Service.findByCostGreaterThanOrTitleStartingWith(cost, prefix);
	}
}
