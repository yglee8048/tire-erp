package com.minsoo.co.tireerp.domain.repository.management;

import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatternRepository extends JpaRepository<Pattern, Long> {

    List<Pattern> findAllByBrand(Brand brand);

    boolean existsByBrandAndName(Brand brand, String name);
}
