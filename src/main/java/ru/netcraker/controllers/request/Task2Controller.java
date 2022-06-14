package ru.netcraker.controllers.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netcraker.extraEntityForRequest.task2.BookTitleAndCost;
import ru.netcraker.extraEntityForRequest.task2.BuyerAreaOfResidence;
import ru.netcraker.extraEntityForRequest.task2.PurchaseMonth;
import ru.netcraker.service.Task2Service;

import java.util.List;

@RestController
@RequestMapping("/api/task2")
public class Task2Controller {

	@Autowired
	private Task2Service task2Service;

	@GetMapping("/a")
	private List<BookTitleAndCost> findAllTitleAndCost() {
		return task2Service.findAllTitleAndCost();
	}

	@GetMapping("/b")
	public List<BuyerAreaOfResidence> findAllAreaOfResidence() {
		return task2Service.findAllAreaOfResidence();
	}

	@GetMapping("/c")
	public List<PurchaseMonth> findAllMonth() {
		return task2Service.findAllMonth();
	}

}
