package com.minsoo.co.tireerp.domain.service.management;

import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Vendor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class VendorServiceTest {

    @Autowired
    VendorService vendorService;

    @Test
    @DisplayName("매입처 이름 중복 생성 테스트")
    @Transactional
    void vendor_name_duplicate_create_test() {
        // when
        vendorService.create(EntitySnippet.vendor());

        // then
        assertThrows(BadRequestException.class, () -> vendorService.create(EntitySnippet.vendor()));
    }

    @Test
    @DisplayName("매입처 수정 테스트")
    @Transactional
    void vendor_update_test() {
        // given
        Vendor create = vendorService.create(EntitySnippet.vendor());

        // when
        Vendor update = vendorService.update(create.getId(), EntitySnippet.vendor2());

        // then
        assertEquals(update.getName(), EntitySnippet.vendor2().getName());
    }

    @Test
    @DisplayName("매입처 이름 중복 수정 테스트")
    @Transactional
    void vendor_name_duplicate_update_test() {
        // when
        vendorService.create(EntitySnippet.vendor());
        Vendor vendor2 = vendorService.create(EntitySnippet.vendor2());

        // then
        assertThrows(BadRequestException.class, () -> vendorService.update(vendor2.getId(), EntitySnippet.vendor()));
    }
}