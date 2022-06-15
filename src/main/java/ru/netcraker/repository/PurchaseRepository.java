package ru.netcraker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcraker.entity.Purchase;

import java.util.Calendar;
import java.util.List;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	List<Purchase> findAllBySummaGreaterThanEqual(Integer summa);

	List<Purchase> findAllByOrderByOrderNumberAsc();
}
