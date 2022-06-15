package ru.netcraker.controllers.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netcraker.entity.Shop;
import ru.netcraker.extraEntityForRequest.task5.HistoryPurchaseBook;
import ru.netcraker.extraEntityForRequest.task5.LastNameAndLocationAreaAndData;
import ru.netcraker.extraEntityForRequest.task5.OrderNumAndLastNameAndData;
import ru.netcraker.service.Task5Service;

import java.util.List;

@RestController
@RequestMapping("/api/task5")
public class Task5Controller {
	@Autowired
	private Task5Service task5Service;

	@GetMapping("/a/{summa}")
	private List<OrderNumAndLastNameAndData> findAllOrderNumAndLastNameAndData(@PathVariable Integer summa) {
		return task5Service.findAllOrderNumAndLastNameAndData(summa);
	}

	@GetMapping("/b/{month}")
	public List<LastNameAndLocationAreaAndData> findAllLastNameAndLocationAreaAndData(@PathVariable String month) {
		return task5Service.findAllLastNameAndLocationAreaAndData(month);
	}

	@GetMapping("/c")
	public List<Shop> findAllByCommissionBetweenAndLocationAreaNotContaining(
			@RequestParam Integer commission, @RequestParam Integer commission2, @RequestParam String locationArea) {
		return task5Service.findAllByCommissionBetweenAndLocationAreaNotContaining(commission, commission2, locationArea);
	}

	@GetMapping("/d")
	public List<HistoryPurchaseBook> findAllHistoryRurchaiseBook(@RequestParam Integer currentVolume) {
		return task5Service.findAllHistoryRurchaiseBook(currentVolume);
	}
}

