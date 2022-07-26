package com.minsoo.co.tireerp.domain.service.management;

import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PatternServiceTest {

    @Autowired
    PatternService patternService;

    @Autowired
    BrandService brandService;

    @Test
    @DisplayName("패턴 이름 중복 생성 테스트")
    @Transactional
    void pattern_name_duplicate_create_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());

        // when
        patternService.create(EntitySnippet.pattern(), brand);

        // then
        assertThrows(BadRequestException.class, () -> patternService.create(EntitySnippet.pattern(), brand));
    }

    @Test
    @DisplayName("패턴 수정 테스트")
    @Transactional
    void pattern_update_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern create = patternService.create(EntitySnippet.pattern(), brand);

        // when
        Pattern update = patternService.update(create.getId(), EntitySnippet.pattern2(), brand);

        // then
        assertEquals(update.getName(), EntitySnippet.pattern2().getName());
    }

    @Test
    @DisplayName("패턴 이름 중복 수정 테스트")
    @Transactional
    void pattern_name_duplicate_update_test() {
        // given
        Brand brand = brandService.create(EntitySnippet.brand());

        // when
        patternService.create(EntitySnippet.pattern(), brand);
        Pattern pattern2 = patternService.create(EntitySnippet.pattern2(), brand);

        // then
        assertThrows(BadRequestException.class, () -> patternService.update(pattern2.getId(), EntitySnippet.pattern(), brand));
    }
}