package com.minsoo.co.tireerp.domain.service.stock;

import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.minsoo.co.tireerp.domain.service.management.BrandService;
import com.minsoo.co.tireerp.domain.service.management.PatternService;
import com.minsoo.co.tireerp.domain.service.management.WarehouseService;
import com.minsoo.co.tireerp.domain.service.tire.TireDotService;
import com.minsoo.co.tireerp.domain.service.tire.TireService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceTest {

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
    @DisplayName("재고 생성 테스트")
    @Transactional
    void stock_create_test() {
        // given
        Warehouse warehouse = warehouseService.create(EntitySnippet.warehouse());
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);
        TireDot tireDot = tireDotService.create(EntitySnippet.tireDot(), tire);

        // when
        Stock stock1 = stockService.createOrUpdate(EntitySnippet.stock(), tireDot, warehouse);
        Stock stock2 = stockService.createOrUpdate(EntitySnippet.stock2(), tireDot, warehouse);

        // then
        assertNotNull(stock1.getId());
        assertNotNull(stock2.getId());
        assertNotEquals(stock1.getId(), stock2.getId());
    }

    @Test
    @DisplayName("재고 수정 테스트")
    @Transactional
    void stock_update_test() {
        // given
        Warehouse warehouse = warehouseService.create(EntitySnippet.warehouse());
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);
        TireDot tireDot = tireDotService.create(EntitySnippet.tireDot(), tire);

        // when
        Stock stock1 = stockService.createOrUpdate(EntitySnippet.stock(), tireDot, warehouse);
        Stock stock2 = stockService.createOrUpdate(EntitySnippet.stock(), tireDot, warehouse);

        // then
        assertEquals(stock1.getId(), stock2.getId());
        assertEquals(stock1.getQuantity(), EntitySnippet.stock().getQuantity() * 2);
    }
}