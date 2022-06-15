package ru.netcraker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netcraker.entity.Shop;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
	List<Shop> findAllByLocationArea(String locationArea);

	List<Shop> findAllByCommissionBetweenAndLocationAreaNotContaining(Integer commission, Integer commission2, String locationArea);
}
