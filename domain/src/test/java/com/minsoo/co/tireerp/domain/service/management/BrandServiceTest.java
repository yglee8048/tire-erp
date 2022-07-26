package com.minsoo.co.tireerp.domain.service.management;

import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BrandServiceTest {

    @Autowired
    BrandService brandService;

    @Test
    @DisplayName("제조사 이름 중복 생성 테스트")
    @Transactional
    void brand_name_duplicate_create_test() {
        // when
        brandService.create(EntitySnippet.brand());

        // then
        assertThrows(BadRequestException.class, () -> brandService.create(EntitySnippet.brand()));
    }

    @Test
    @DisplayName("제조사 수정 테스트")
    @Transactional
    void brand_update_test() {
        // given
        Brand create = brandService.create(EntitySnippet.brand());

        // when
        Brand update = brandService.update(create.getId(), EntitySnippet.brand2());

        // then
        assertEquals(update.getName(), EntitySnippet.brand2().getName());
    }

    @Test
    @DisplayName("제조사 이름 중복 수정 테스트")
    @Transactional
    void brand_name_duplicate_update_test() {
        // when
        brandService.create(EntitySnippet.brand());
        Brand brand2 = brandService.create(EntitySnippet.brand2());

        // then
        assertThrows(BadRequestException.class, () -> brandService.update(brand2.getId(), EntitySnippet.brand()));
    }
}