package com.minsoo.co.tireerp.domain.repository.pruchase;

import com.minsoo.co.tireerp.domain.entity.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
