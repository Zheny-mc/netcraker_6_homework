package ru.netcraker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netcraker.entity.Buyer;
import ru.netcraker.exception.BuyerNotFoundException;
import ru.netcraker.repository.BuyerRepository;

import java.util.List;

@RestController
@RequestMapping("/api/buyer")
@RequiredArgsConstructor
public class BuyerController {

	private final BuyerRepository buyerRepository;

    @GetMapping("/all")
    public List<Buyer> getAllBuyer() {
    	return buyerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Buyer getBuyerId(@PathVariable("id") long id) throws BuyerNotFoundException {
        return buyerRepository.findById(id)
		        .orElseThrow(() -> new BuyerNotFoundException("Invalid buyer Id:" + id));
    }

	@PostMapping("/")
	public ResponseEntity addBuyer(@RequestBody Buyer buyer) {
		buyerRepository.save(buyer);

		return new ResponseEntity<>("Buyer create!", HttpStatus.OK);
	}

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity updateBuyer(@PathVariable("id") long id, @RequestBody Buyer buyer)
		    throws BuyerNotFoundException {
	    Buyer putBuyer = buyerRepository.findById(id).map(i -> {
	        i.setLastName(buyer.getLastName());
	        i.setAreaOfResidence(buyer.getAreaOfResidence());
		    i.setDiscount(buyer.getDiscount());
	        return i;
	    }).orElseThrow(() -> new BuyerNotFoundException("Invalid buyer Id:" + id));

	    buyerRepository.save(putBuyer);

        return new ResponseEntity<>("Buyer put!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Buyer deleteBuyer(@PathVariable("id") long id)
		    throws BuyerNotFoundException {
	    Buyer buyer = buyerRepository.findById(id).orElseThrow(() -> new BuyerNotFoundException("Invalid buyer Id:" + id));
	    buyerRepository.delete(buyer);

	    return buyer;
    }

	@PatchMapping("/lastName/{id}")
	public @ResponseBody ResponseEntity UpdateLastNameBuyer(@PathVariable("id") long id, @RequestBody String lastName)
			throws BuyerNotFoundException {
		Buyer putBuyer = buyerRepository.findById(id).map(i -> {
			i.setLastName(lastName);
			return i;
		}).orElseThrow(() -> new BuyerNotFoundException("Invalid buyer Id:" + id));

		buyerRepository.save(putBuyer);

		return new ResponseEntity<>("Buyer lastName patch!", HttpStatus.OK);
	}

	@PatchMapping("/areaOfResidence/{id}")
	public @ResponseBody ResponseEntity UpdateCostBuyer(@PathVariable("id") long id, @RequestParam String areaOfResidence)
			throws BuyerNotFoundException {
		Buyer putBuyer = buyerRepository.findById(id).map(i -> {
			i.setAreaOfResidence(areaOfResidence);
			return i;
		}).orElseThrow(() -> new BuyerNotFoundException("Invalid buyer Id:" + id));

		buyerRepository.save(putBuyer);

		return new ResponseEntity<>("Buyer areaOfResidence patch!", HttpStatus.OK);
	}

	@PatchMapping("/discount/{id}")
	public @ResponseBody ResponseEntity UpdateWarehouseBuyer(@PathVariable("id") long id, @RequestParam Integer discount)
			throws BuyerNotFoundException {
		Buyer putBuyer = buyerRepository.findById(id).map(i -> {
			i.setDiscount(discount);
			return i;
		}).orElseThrow(() -> new BuyerNotFoundException("Invalid buyer Id:" + id));

		buyerRepository.save(putBuyer);

		return new ResponseEntity<>("Buyer warehouse patch!", HttpStatus.OK);
	}

}