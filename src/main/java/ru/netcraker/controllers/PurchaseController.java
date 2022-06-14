package ru.netcraker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netcraker.entity.Book;
import ru.netcraker.entity.Buyer;
import ru.netcraker.entity.Purchase;
import ru.netcraker.entity.Shop;
import ru.netcraker.exception.PurchaseNotFoundException;
import ru.netcraker.repository.PurchaseRepository;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

	private final PurchaseRepository purchaseRepository;

    @GetMapping("/all")
    public List<Purchase> getAllPurchase() {
    	return purchaseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Purchase getPurchaseId(@PathVariable("id") long id) throws PurchaseNotFoundException {
        return purchaseRepository.findById(id)
		        .orElseThrow(() -> new PurchaseNotFoundException("Invalid purchase Id:" + id));
    }

	@PostMapping
	public ResponseEntity addPurchase(@RequestBody Purchase purchase) {
		purchaseRepository.save(purchase);

		return new ResponseEntity<>("Purchase create!", HttpStatus.OK);
	}

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity updatePurchase(@PathVariable("id") long id, @RequestBody Purchase purchase)
		    throws PurchaseNotFoundException {
	    Purchase putPurchase = purchaseRepository.findById(id).map(i -> {
	        i.setDataTime(purchase.getCalendar());
		    i.setShopList(purchase.getShopList());
		    i.setBuyerList(purchase.getBuyerList());
	        i.setBookList(purchase.getBookList());
		    i.setVolume(purchase.getVolume());
	        i.setSumma(purchase.getSumma());
	        return i;
	    }).orElseThrow(() -> new PurchaseNotFoundException("Invalid purchase Id:" + id));

	    purchaseRepository.save(putPurchase);

        return new ResponseEntity<>("Purchase put!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Purchase deleteBook(@PathVariable("id") long id)
		    throws PurchaseNotFoundException {
	    Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new PurchaseNotFoundException("Invalid purchase Id:" + id));
	    purchaseRepository.delete(purchase);

	    return purchase;
    }
	//---------------------------------------------

	@PatchMapping("/seller/{id}")
	public @ResponseBody ResponseEntity UpdateSellerPurchase(@PathVariable("id") long id, @RequestBody Shop seller)
			throws PurchaseNotFoundException {
		Purchase putPurchase = purchaseRepository.findById(id).map(i -> {
			i.setShopList(List.of(seller));
			return i;
		}).orElseThrow(() -> new PurchaseNotFoundException("Invalid purchase Id:" + id));

		purchaseRepository.save(putPurchase);

		return new ResponseEntity<>("Purchase seller patch!", HttpStatus.OK);
	}

	@PatchMapping("/buyer/{id}")
	public @ResponseBody ResponseEntity UpdateBuyerPurchase(@PathVariable("id") long id, @RequestParam Buyer buyer)
			throws PurchaseNotFoundException {
		Purchase putPurchase = purchaseRepository.findById(id).map(i -> {
			i.setBuyerList(List.of(buyer));
			return i;
		}).orElseThrow(() -> new PurchaseNotFoundException("Invalid purchase Id:" + id));

		purchaseRepository.save(putPurchase);

		return new ResponseEntity<>("Purchase buyer patch!", HttpStatus.OK);
	}

	@PatchMapping("/book/{id}")
	public @ResponseBody ResponseEntity UpdateBookPurchase(@PathVariable("id") long id, @RequestParam Book book)
			throws PurchaseNotFoundException {
		Purchase putPurchase = purchaseRepository.findById(id).map(i -> {
			i.setBookList(List.of(book));
			return i;
		}).orElseThrow(() -> new PurchaseNotFoundException("Invalid purchase Id:" + id));

		purchaseRepository.save(putPurchase);

		return new ResponseEntity<>("Purchase book patch!", HttpStatus.OK);
	}

	@PatchMapping("/volume/{id}")
	public @ResponseBody ResponseEntity UpdateVolumePurchase(@PathVariable("id") long id, @RequestParam Integer volume)
			throws PurchaseNotFoundException {
		Purchase putPurchase = purchaseRepository.findById(id).map(i -> {
			i.setVolume(volume);
			return i;
		}).orElseThrow(() -> new PurchaseNotFoundException("Invalid purchase Id:" + id));

		purchaseRepository.save(putPurchase);

		return new ResponseEntity<>("Purchase volume patch!", HttpStatus.OK);
	}

	@PatchMapping("/summa/{id}")
	public @ResponseBody ResponseEntity UpdateSummaPurchase(@PathVariable("id") long id, @RequestParam Integer summa)
			throws PurchaseNotFoundException {
		Purchase putPurchase = purchaseRepository.findById(id).map(i -> {
			i.setSumma(summa);
			return i;
		}).orElseThrow(() -> new PurchaseNotFoundException("Invalid purchase Id:" + id));

		purchaseRepository.save(putPurchase);

		return new ResponseEntity<>("Purchase summa patch!", HttpStatus.OK);
	}
}