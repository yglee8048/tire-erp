package com.minsoo.co.tireerp.domain.service.purchase;

import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.management.Vendor;
import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import com.minsoo.co.tireerp.domain.entity.purchase.Purchase;
import com.minsoo.co.tireerp.domain.entity.purchase.PurchaseContent;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.minsoo.co.tireerp.domain.service.management.BrandService;
import com.minsoo.co.tireerp.domain.service.management.PatternService;
import com.minsoo.co.tireerp.domain.service.management.VendorService;
import com.minsoo.co.tireerp.domain.service.management.WarehouseService;
import com.minsoo.co.tireerp.domain.service.stock.StockService;
import com.minsoo.co.tireerp.domain.service.tire.TireDotService;
import com.minsoo.co.tireerp.domain.service.tire.TireService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PurchaseContentServiceTest {

    @Autowired
    PurchaseContentService purchaseContentService;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    VendorService vendorService;

    @Autowired
    StockService stockService;

    @Autowired
    TireDotService tireDotService;

    @Autowired
    TireService tireService;

    @Autowired
    PatternService patternService;

    @Autowired
    BrandService brandService;

    @Autowired
    WarehouseService warehouseService;

    @Test
    @DisplayName("매입 항목 생성 테스트")
    @Transactional
    void purchase_content_create_test() {
        // given
        Vendor vendor = vendorService.create(EntitySnippet.vendor());
        Purchase purchase = purchaseService.create(EntitySnippet.purchase(), vendor);
        Warehouse warehouse = warehouseService.create(EntitySnippet.warehouse());
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);
        TireDot tireDot = tireDotService.create(EntitySnippet.tireDot(), tire);
        Stock stock = stockService.createOrUpdate(EntitySnippet.stock(), tireDot, warehouse);

        // when
        PurchaseContent purchaseContent = purchaseContentService.create(EntitySnippet.purchaseContent(), purchase, stock);

        // then
        assertNotNull(purchaseContent.getId());
    }

    @Test
    @DisplayName("매입 항목 수정 테스트")
    @Transactional
    void purchase_content_update_test() {
        // given
        Vendor vendor = vendorService.create(EntitySnippet.vendor());
        Purchase purchase = purchaseService.create(EntitySnippet.purchase(), vendor);
        Warehouse warehouse = warehouseService.create(EntitySnippet.warehouse());
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);
        TireDot tireDot = tireDotService.create(EntitySnippet.tireDot(), tire);
        Stock stock = stockService.createOrUpdate(EntitySnippet.stock(), tireDot, warehouse);

        // when
        PurchaseContent create = purchaseContentService.create(EntitySnippet.purchaseContent(), purchase, stock);
        PurchaseContent update = purchaseContentService.update(create.getId(), EntitySnippet.purchaseContent2(), stock);

        // then
        assertEquals(update.getPrice(), EntitySnippet.purchaseContent2().getPrice());
        assertEquals(update.getQuantity(), EntitySnippet.purchaseContent2().getQuantity());
    }
}