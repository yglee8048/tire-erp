package com.minsoo.co.tireerp.domain.repository.tire;

import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireMemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TireMemoRepository extends JpaRepository<TireMemo, Long> {

    List<TireMemo> findAllByTire(Tire tire);
}
