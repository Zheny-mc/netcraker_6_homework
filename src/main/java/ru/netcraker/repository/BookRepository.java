package ru.netcraker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcraker.entity.Book;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByCostGreaterThanOrTitleStartingWith(Integer cost, String prefix);
}
