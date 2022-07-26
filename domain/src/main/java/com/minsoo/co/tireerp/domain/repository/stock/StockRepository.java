package com.minsoo.co.tireerp.domain.repository.stock;

import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
