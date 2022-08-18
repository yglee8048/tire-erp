package com.minsoo.co.tireerp.domain.service.tire;

import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireMemo;
import com.minsoo.co.tireerp.domain.service.management.BrandService;
import com.minsoo.co.tireerp.domain.service.management.PatternService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TireMemoServiceTest {

    @Autowired
    TireMemoService tireMemoService;

    @Autowired
    TireService tireService;

    @Autowired
    PatternService patternService;

    @Autowired
    BrandService brandService;

    @Test
    @DisplayName("타이어 메모 생성 테스트")
    @Transactional
    void tire_memo_create_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);

        // when
        TireMemo tireMemo = tireMemoService.create(EntitySnippet.tireMemo(), tire);

        // then
        assertNotNull(tireMemo.getId());
    }

    @Test
    @DisplayName("타이어 메모 수정 테스트")
    @Transactional
    void tire_memo_update_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);

        // when
        TireMemo create = tireMemoService.create(EntitySnippet.tireMemo(), tire);
        TireMemo update = tireMemoService.update(create.getId(), EntitySnippet.tireMemo2());

        // then
        assertEquals(update.getMemo(), EntitySnippet.tireMemo2().getMemo());
        assertNotEquals(update.getMemo(), EntitySnippet.tireMemo().getMemo());
    }
}