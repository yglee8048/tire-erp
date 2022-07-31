package com.minsoo.co.tireerp.domain.service.sale;

import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.sale.Sale;
import com.minsoo.co.tireerp.domain.entity.sale.SaleContent;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import com.minsoo.co.tireerp.domain.repository.sale.SaleContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SaleContentService {

    private final SaleContentRepository saleContentRepository;

    @Transactional(readOnly = true)
    public SaleContent findById(Long id) {
        return saleContentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("sale-content", id));
    }

    public SaleContent create(SaleContent create, Sale sale, Stock stock) {
        return saleContentRepository.save(create
                .setSale(sale)
                .setStock(stock));
    }

    public SaleContent update(Long id, SaleContent update, Stock stock) {
        return findById(id)
                .update(update, stock);
    }

    public void deleteById(Long id) {
        saleContentRepository.delete(findById(id));
    }
}
