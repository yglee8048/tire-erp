package com.minsoo.co.tireerp.domain.service.purchase;

import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.purchase.Purchase;
import com.minsoo.co.tireerp.domain.entity.purchase.PurchaseContent;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import com.minsoo.co.tireerp.domain.repository.pruchase.PurchaseContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseContentService {

    private final PurchaseContentRepository purchaseContentRepository;

    @Transactional(readOnly = true)
    public PurchaseContent findById(Long id) {
        return purchaseContentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("purchase-content", id));
    }

    public PurchaseContent create(PurchaseContent create, Purchase purchase, Stock stock) {
        return purchaseContentRepository.save(
                create.setPurchase(purchase)
                        .setStock(stock));
    }

    public PurchaseContent update(Long id, PurchaseContent update, Stock stock) {
        return findById(id).update(update, stock);
    }

    public void deleteById(Long id) {
        purchaseContentRepository.delete(findById(id));
    }
}
