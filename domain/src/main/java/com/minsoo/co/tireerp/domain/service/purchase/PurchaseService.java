package com.minsoo.co.tireerp.domain.service.purchase;

import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.management.Vendor;
import com.minsoo.co.tireerp.domain.entity.purchase.Purchase;
import com.minsoo.co.tireerp.domain.repository.pruchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Transactional(readOnly = true)
    public Purchase findById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("purchase", id));
    }

    public Purchase create(Purchase create, Vendor vendor) {
        return purchaseRepository.save(create.setVendor(vendor));
    }

    public Purchase update(Long id, Purchase update, Vendor vendor) {
        return findById(id).update(update, vendor);
    }

    public void deleteById(Long id) {
        purchaseRepository.delete(findById(id));
    }
}
