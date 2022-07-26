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

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    @Transactional(readOnly = true)
    public Stock findById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("stock", id));
    }

    public Stock createOrUpdate(Stock request, TireDot tireDot, Warehouse warehouse) {
        return stockRepository.findByTireDotAndWarehouseAndNickname(tireDot, warehouse, request.getNickname())
                .map(found -> found.addQuantity(request.getQuantity()))
                .map(found -> found.setLock(request.getLock()))
                .orElseGet(() -> stockRepository.save(request.setTireDot(tireDot).setWarehouse(warehouse)));
    }

    public void deleteById(Long id) {
        stockRepository.delete(findById(id));
    }
}
