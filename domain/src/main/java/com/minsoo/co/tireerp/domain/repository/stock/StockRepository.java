package com.minsoo.co.tireerp.domain.repository.stock;

import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByTireDotAndWarehouseAndNickname(TireDot tireDot, Warehouse warehouse, String nickname);
}
