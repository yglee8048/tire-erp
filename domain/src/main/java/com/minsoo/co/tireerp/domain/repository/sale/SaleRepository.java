package com.minsoo.co.tireerp.domain.repository.sale;

import com.minsoo.co.tireerp.domain.entity.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
