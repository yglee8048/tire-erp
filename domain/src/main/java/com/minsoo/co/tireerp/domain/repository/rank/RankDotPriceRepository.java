package com.minsoo.co.tireerp.domain.repository.rank;

import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import com.minsoo.co.tireerp.domain.entity.rank.RankDotPrice;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankDotPriceRepository extends JpaRepository<RankDotPrice, Long> {

    Optional<RankDotPrice> findByRankAndTireDot(Rank rank, TireDot tireDot);
}
