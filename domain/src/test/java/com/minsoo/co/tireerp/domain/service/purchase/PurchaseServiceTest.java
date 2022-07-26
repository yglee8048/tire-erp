package com.minsoo.co.tireerp.domain.service.purchase;

import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Vendor;
import com.minsoo.co.tireerp.domain.entity.purchase.Purchase;
import com.minsoo.co.tireerp.domain.service.management.VendorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseServiceTest {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    VendorService vendorService;

    @Test
    @DisplayName("매입 생성 테스트")
    @Transactional
    void purchase_create_test() {
        // given
        Vendor vendor = vendorService.create(EntitySnippet.vendor());

        // when
        Purchase create = purchaseService.create(EntitySnippet.purchase(), vendor);

        // then
        assertNotNull(create.getId());
    }

    @Test
    @DisplayName("매입 항목 생성 테스트")
    @Transactional
    void purchase_update_test() {
        // given
        Vendor vendor = vendorService.create(EntitySnippet.vendor());
        Vendor vendor2 = vendorService.create(EntitySnippet.vendor2());

        // when
        Purchase create = purchaseService.create(EntitySnippet.purchase(), vendor);
        Purchase update = purchaseService.update(create.getId(), EntitySnippet.purchase(), vendor2);

        // then
        assertEquals(update.getVendor().getId(), vendor2.getId());
        assertNotEquals(update.getVendor().getId(), vendor.getId());
    }
}