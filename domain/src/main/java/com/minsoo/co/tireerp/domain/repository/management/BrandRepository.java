package com.minsoo.co.tireerp.domain.repository.management;

import com.minsoo.co.tireerp.domain.entity.management.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByName(String name);
}
