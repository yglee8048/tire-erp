package com.minsoo.co.tireerp.domain.repository.tire;

import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TireRepository extends JpaRepository<Tire, Long> {

    boolean existsByTireCode(String tireCode);
}
