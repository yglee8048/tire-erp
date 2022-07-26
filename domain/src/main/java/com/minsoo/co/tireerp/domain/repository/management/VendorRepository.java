package com.minsoo.co.tireerp.domain.repository.management;

import com.minsoo.co.tireerp.domain.entity.management.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    boolean existsByName(String name);
}
