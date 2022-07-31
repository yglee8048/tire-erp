package com.minsoo.co.tireerp.domain.repository.sale;

import com.minsoo.co.tireerp.domain.entity.sale.Delivery;
import com.minsoo.co.tireerp.domain.entity.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    boolean existsBySale(Sale sale);
}
