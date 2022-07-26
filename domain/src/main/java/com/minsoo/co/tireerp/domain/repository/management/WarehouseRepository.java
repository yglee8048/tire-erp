package com.minsoo.co.tireerp.domain.repository.management;

import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    boolean existsByName(String name);
}
