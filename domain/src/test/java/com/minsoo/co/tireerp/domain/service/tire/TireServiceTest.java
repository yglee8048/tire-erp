package com.minsoo.co.tireerp.domain.service.tire;

import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.service.management.BrandService;
import com.minsoo.co.tireerp.domain.service.management.PatternService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TireServiceTest {

    @Autowired
    TireService tireService;

    @Autowired
    PatternService patternService;

    @Autowired
    BrandService brandService;

    @Test
    @DisplayName("타이어 생성 테스트")
    @Transactional
    void tire_create_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);

        // when
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);

        // then
        assertNotNull(tire.getId());
    }

    @Test
    @DisplayName("타이어 코드 중복 생성 테스트")
    @Transactional
    void tire_code_duplicate_create_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);

        // when
        tireService.create(EntitySnippet.tire(), pattern);

        // then
        assertThrows(BadRequestException.class, () -> tireService.create(EntitySnippet.tire(), pattern));
    }

    @Test
    @DisplayName("타이어 수정 테스트")
    @Transactional
    void tire_code_update_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);

        // when
        Tire update = tireService.update(tire.getId(), EntitySnippet.tire2(), pattern);

        // then
        assertEquals(update.getTireCode(), EntitySnippet.tire2().getTireCode());
        assertNotEquals(update.getTireCode(), EntitySnippet.tire().getTireCode());
    }

    @Test
    @DisplayName("타이어 코드 중복 수정 테스트")
    @Transactional
    void tire_code_duplicate_update_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);

        // when
        tireService.create(EntitySnippet.tire(), pattern);
        Tire create = tireService.create(EntitySnippet.tire2(), pattern);

        // then
        assertThrows(BadRequestException.class, () -> tireService.update(create.getId(), EntitySnippet.tire(), pattern));
    }
}