package com.minsoo.co.tireerp.domain.service.management;

import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class WarehouseServiceTest {

    @Autowired
    WarehouseService warehouseService;

    @Test
    @DisplayName("창고 이름 중복 생성 테스트")
    @Transactional
    void warehouse_name_duplicate_create_test() {
        // when
        warehouseService.create(EntitySnippet.warehouse());

        // then
        assertThrows(BadRequestException.class, () -> warehouseService.create(EntitySnippet.warehouse()));
    }

    @Test
    @DisplayName("창고 수정 테스트")
    @Transactional
    void warehouse_update_test() {
        // given
        Warehouse create = warehouseService.create(EntitySnippet.warehouse());

        // when
        Warehouse update = warehouseService.update(create.getId(), EntitySnippet.warehouse2());

        // then
        assertEquals(update.getName(), EntitySnippet.warehouse2().getName());
    }

    @Test
    @DisplayName("창고 이름 중복 수정 테스트")
    @Transactional
    void warehouse_name_duplicate_update_test() {
        // when
        warehouseService.create(EntitySnippet.warehouse());
        Warehouse warehouse2 = warehouseService.create(EntitySnippet.warehouse2());

        // then
        assertThrows(BadRequestException.class, () -> warehouseService.update(warehouse2.getId(), EntitySnippet.warehouse()));
    }
}