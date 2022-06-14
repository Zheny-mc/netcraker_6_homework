package ru.netcraker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netcraker.entity.Book;
import ru.netcraker.exception.BookNotFoundException;
import ru.netcraker.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

	private final BookRepository bookRepository;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
    	return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookId(@PathVariable("id") long id) throws BookNotFoundException {
        return bookRepository.findById(id)
		        .orElseThrow(() -> new BookNotFoundException("Invalid book Id:" + id));
    }

	@PostMapping
	public ResponseEntity addBook(@RequestBody Book book) {
		bookRepository.save(book);

		return new ResponseEntity<>("Book create!", HttpStatus.OK);
	}

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity updateBook(@PathVariable("id") long id, @RequestBody Book book)
		    throws BookNotFoundException {
	    Book putBook = bookRepository.findById(id).map(i -> {
	        i.setTitle(book.getTitle());
	        i.setCost(book.getCost());
		    i.setVolume(book.getVolume());
	        i.setWarehouse(book.getWarehouse());
	        return i;
	    }).orElseThrow(() -> new BookNotFoundException("Invalid book Id:" + id));

	    bookRepository.save(putBook);

        return new ResponseEntity<>("Book put!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable("id") long id)
		    throws BookNotFoundException {
	    Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Invalid book Id:" + id));
	    bookRepository.delete(book);

	    return book;
    }

	@PatchMapping("/title/{id}")
	public @ResponseBody ResponseEntity UpdateTitleBook(@PathVariable("id") long id, @RequestBody String title) throws BookNotFoundException {
		Book putBook = bookRepository.findById(id).map(i -> {
			i.setTitle(title);
			return i;
		}).orElseThrow(() -> new BookNotFoundException("Invalid user Id:" + id));

		bookRepository.save(putBook);

		return new ResponseEntity<>("Book title patch!", HttpStatus.OK);
	}

	@PatchMapping("/cost/{id}")
	public @ResponseBody ResponseEntity UpdateCostBook(@PathVariable("id") long id, @RequestParam Integer cost)
			throws BookNotFoundException {
		Book putBook = bookRepository.findById(id).map(i -> {
			i.setCost(cost);
			return i;
		}).orElseThrow(() -> new BookNotFoundException("Invalid book Id:" + id));

		bookRepository.save(putBook);

		return new ResponseEntity<>("Book cost patch!", HttpStatus.OK);
	}

	@PatchMapping("/warehouse/{id}")
	public @ResponseBody ResponseEntity UpdateWarehouseBook(@PathVariable("id") long id, @RequestParam String warehouse)
			throws BookNotFoundException {
		Book putBook = bookRepository.findById(id).map(i -> {
			i.setWarehouse(warehouse);
			return i;
		}).orElseThrow(() -> new BookNotFoundException("Invalid book Id:" + id));

		bookRepository.save(putBook);

		return new ResponseEntity<>("Book warehouse patch!", HttpStatus.OK);
	}

	@PatchMapping("/volume/{id}")
	public @ResponseBody ResponseEntity UpdateVolumeBook(@PathVariable("id") long id, @RequestParam Integer volume)
			throws BookNotFoundException {
		Book putBook = bookRepository.findById(id).map(i -> {
			i.setVolume(volume);
			return i;
		}).orElseThrow(() -> new BookNotFoundException("Invalid book Id:" + id));

		bookRepository.save(putBook);

		return new ResponseEntity<>("Book volume patch!", HttpStatus.OK);
	}
}