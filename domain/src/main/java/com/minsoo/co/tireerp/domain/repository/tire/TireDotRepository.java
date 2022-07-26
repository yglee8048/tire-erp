package com.minsoo.co.tireerp.domain.repository.tire;

import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TireDotRepository extends JpaRepository<TireDot, Long> {

    boolean existsByTireAndDot(Tire tire, String dot);
}
