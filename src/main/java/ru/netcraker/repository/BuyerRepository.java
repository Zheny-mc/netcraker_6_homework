package ru.netcraker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcraker.entity.Buyer;

import java.util.List;


public interface BuyerRepository extends JpaRepository<Buyer, Long> {

	List<Buyer> findAllByAreaOfResidence(String areaOfResidence);

}
