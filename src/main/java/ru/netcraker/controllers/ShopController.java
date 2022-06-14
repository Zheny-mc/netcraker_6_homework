package ru.netcraker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netcraker.entity.Shop;
import ru.netcraker.exception.ShopNotFoundException;
import ru.netcraker.repository.ShopRepository;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShopController {

	private final ShopRepository shopRepository;

    @GetMapping("/all")
    public List<Shop> getAllShops() {
    	return shopRepository.findAll();
    }

    @GetMapping("/{id}")
    public Shop getShopId(@PathVariable("id") long id) throws ShopNotFoundException {
        return shopRepository.findById(id)
		        .orElseThrow(() -> new ShopNotFoundException("Invalid shop Id:" + id));
    }

	@PostMapping
	public @ResponseBody ResponseEntity addShop(@RequestBody Shop shop) {
		shopRepository.save(shop);

		return new ResponseEntity<>("Shop create!", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public Shop deleteShop(@PathVariable("id") long id)
			throws ShopNotFoundException {
		Shop shop = shopRepository.findById(id)
				.orElseThrow(() -> new ShopNotFoundException("Invalid shop Id:" + id));
		shopRepository.delete(shop);

		return shop;
	}

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity updateShop(@PathVariable("id") long id, @RequestBody Shop shop)
		    throws ShopNotFoundException {
	    Shop putBook = shopRepository.findById(id).map(i -> {
	        i.setTitle(shop.getTitle());
	        i.setLocationArea(shop.getLocationArea());
		    i.setCommission(shop.getCommission());
	        return i;
	    }).orElseThrow(() -> new ShopNotFoundException("Invalid shop Id:" + id));

	    shopRepository.save(putBook);

        return new ResponseEntity<>("Shop put!", HttpStatus.OK);
    }

	@PatchMapping("/title/{id}")
	public @ResponseBody ResponseEntity UpdateTitleShop(@PathVariable("id") long id, @RequestBody String title)
			throws ShopNotFoundException {
		Shop putShop = shopRepository.findById(id).map(i -> {
			i.setTitle(title);
			return i;
		}).orElseThrow(() -> new ShopNotFoundException("Invalid shop Id:" + id));

		shopRepository.save(putShop);

		return new ResponseEntity<>("Book title patch!", HttpStatus.OK);
	}

	@PatchMapping("/locationArea/{id}")
	public @ResponseBody ResponseEntity UpdateLocationAreaShop(@PathVariable("id") long id, @RequestParam String locationArea)
			throws ShopNotFoundException {
		Shop putShop = shopRepository.findById(id).map(i -> {
			i.setLocationArea(locationArea);
			return i;
		}).orElseThrow(() -> new ShopNotFoundException("Invalid shop Id:" + id));

		shopRepository.save(putShop);

		return new ResponseEntity<>("Book locationArea patch!", HttpStatus.OK);
	}

	@PatchMapping("/commission/{id}")
	public @ResponseBody ResponseEntity UpdateCommissionShop(@PathVariable("id") long id, @RequestParam Integer commission)
			throws ShopNotFoundException {
		Shop putShop = shopRepository.findById(id).map(i -> {
			i.setCommission(commission);
			return i;
		}).orElseThrow(() -> new ShopNotFoundException("Invalid shop Id:" + id));

		shopRepository.save(putShop);

		return new ResponseEntity<>("Book commission patch!", HttpStatus.OK);
	}



}