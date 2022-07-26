package com.minsoo.co.tireerp.domain.service.tire;

import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.minsoo.co.tireerp.domain.service.management.BrandService;
import com.minsoo.co.tireerp.domain.service.management.PatternService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TireDotServiceTest {

    @Autowired
    TireDotService tireDotService;

    @Autowired
    TireService tireService;

    @Autowired
    PatternService patternService;

    @Autowired
    BrandService brandService;

    @Test
    @DisplayName("타이어 DOT 생성 테스트")
    @Transactional
    void tire_dot_create_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);

        // when
        TireDot tireDot = tireDotService.create(EntitySnippet.tireDot(), tire);

        // then
        assertNotNull(tireDot.getId());
    }

    @Test
    @DisplayName("타이어 DOT 중복 생성 테스트")
    @Transactional
    void tire_dot_duplicate_create_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);

        // when
        tireDotService.create(EntitySnippet.tireDot(), tire);

        // then
        assertThrows(BadRequestException.class, () -> tireDotService.create(EntitySnippet.tireDot(), tire));
    }

    @Test
    @DisplayName("타이어 DOT 수정 테스트")
    @Transactional
    void tire_dot_update_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);
        TireDot create = tireDotService.create(EntitySnippet.tireDot(), tire);

        // when
        TireDot update = tireDotService.update(create.getId(), EntitySnippet.tireDot2(), tire);

        // then
        assertEquals(update.getDot(), EntitySnippet.tireDot2().getDot());
        assertNotEquals(update.getDot(), EntitySnippet.tireDot().getDot());
    }

    @Test
    @DisplayName("타이어 DOT 중복 수정 테스트")
    @Transactional
    void tire_dot_duplicate_update_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);

        // when
        tireDotService.create(EntitySnippet.tireDot(), tire);
        TireDot tireDot = tireDotService.create(EntitySnippet.tireDot2(), tire);

        // then
        assertThrows(BadRequestException.class, () -> tireDotService.update(tireDot.getId(), EntitySnippet.tireDot(), tire));
    }
}