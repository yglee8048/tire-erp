package com.minsoo.co.tireerp.domain.service.rank;

import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import com.minsoo.co.tireerp.domain.entity.rank.RankDotPrice;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.minsoo.co.tireerp.domain.repository.rank.RankDotPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RankDotPriceService {

    private final RankDotPriceRepository rankDotPriceRepository;

    @Transactional(readOnly = true)
    public RankDotPrice findById(Long id) {
        return rankDotPriceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("rank-dot-price", id));
    }

    public RankDotPrice modify(RankDotPrice request, Rank rank, TireDot tireDot) {
        return rankDotPriceRepository.findByRankAndTireDot(rank, tireDot)
                .map(found -> found.update(request))
                .orElseGet(() -> rankDotPriceRepository.save(request
                        .setRank(rank)
                        .setTireDot(tireDot)));
    }

    public void deleteById(Long id) {
        rankDotPriceRepository.delete(findById(id));
    }
}
