package com.minsoo.co.tireerp.domain.service.rank;

import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import com.minsoo.co.tireerp.domain.entity.rank.RankDotPrice;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.minsoo.co.tireerp.domain.service.management.BrandService;
import com.minsoo.co.tireerp.domain.service.management.PatternService;
import com.minsoo.co.tireerp.domain.service.tire.TireDotService;
import com.minsoo.co.tireerp.domain.service.tire.TireService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RankDotPriceServiceTest {

    @Autowired
    RankDotPriceService rankDotPriceService;

    @Autowired
    RankService rankService;

    @Autowired
    TireDotService tireDotService;

    @Autowired
    TireService tireService;

    @Autowired
    PatternService patternService;

    @Autowired
    BrandService brandService;

    @Test
    @DisplayName("등급 dot 가격 생성 테스트")
    @Transactional
    void rank_dot_price_create_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);
        TireDot tireDot = tireDotService.create(EntitySnippet.tireDot(), tire);
        Rank rank = rankService.create(EntitySnippet.rank());

        // when
        RankDotPrice create = rankDotPriceService.modify(EntitySnippet.rankDotPrice(), rank, tireDot);

        // then
        assertNotNull(create.getId());
        assertEquals(create.getRank(), rank);
        assertEquals(create.getTireDot(), tireDot);
    }

    @Test
    @DisplayName("등급 dot 가격 수정 테스트")
    @Transactional
    void rank_dot_price_update_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);
        TireDot tireDot = tireDotService.create(EntitySnippet.tireDot(), tire);
        Rank rank = rankService.create(EntitySnippet.rank());

        // when
        RankDotPrice create = rankDotPriceService.modify(EntitySnippet.rankDotPrice(), rank, tireDot);
        RankDotPrice update = rankDotPriceService.modify(EntitySnippet.rankDotPrice2(), rank, tireDot);

        // then
        assertEquals(create.getId(), update.getId());
        assertEquals(update.getDiscountedPrice(), EntitySnippet.rankDotPrice2().getDiscountedPrice());
        assertNotEquals(update.getDiscountedPrice(), EntitySnippet.rankDotPrice().getDiscountedPrice());
    }
}