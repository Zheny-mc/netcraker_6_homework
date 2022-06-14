package ru.netcraker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcraker.entity.Purchase;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
