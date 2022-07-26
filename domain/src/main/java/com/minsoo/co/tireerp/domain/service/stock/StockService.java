package com.minsoo.co.tireerp.domain.service.stock;

import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.minsoo.co.tireerp.domain.repository.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional(readOnly = true)
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Stock findById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("stock", id));
    }

    public Stock createOrUpdate(Stock stock, TireDot tireDot, Warehouse warehouse) {
        return stockRepository.findByTireDotAndWarehouseAndNickname(tireDot, warehouse, stock.getNickname())
                .map(found -> found.addQuantity(stock.getQuantity()))
                .map(found -> found.setLock(stock.getLock()))
                .orElseGet(() -> stockRepository.save(stock.setTireDot(tireDot).setWarehouse(warehouse)));
    }

    public void deleteById(Long id) {
        stockRepository.delete(findById(id));
    }
}
