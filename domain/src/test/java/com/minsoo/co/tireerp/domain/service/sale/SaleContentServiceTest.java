package com.minsoo.co.tireerp.domain.service.sale;

import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.client.ClientCompany;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import com.minsoo.co.tireerp.domain.entity.sale.Sale;
import com.minsoo.co.tireerp.domain.entity.sale.SaleContent;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.minsoo.co.tireerp.domain.service.client.ClientCompanyService;
import com.minsoo.co.tireerp.domain.service.management.BrandService;
import com.minsoo.co.tireerp.domain.service.management.PatternService;
import com.minsoo.co.tireerp.domain.service.management.WarehouseService;
import com.minsoo.co.tireerp.domain.service.rank.RankService;
import com.minsoo.co.tireerp.domain.service.stock.StockService;
import com.minsoo.co.tireerp.domain.service.tire.TireDotService;
import com.minsoo.co.tireerp.domain.service.tire.TireService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SaleContentServiceTest {

    @Autowired
    SaleContentService saleContentService;

    @Autowired
    SaleService saleService;

    @Autowired
    ClientCompanyService clientCompanyService;

    @Autowired
    RankService rankService;

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
    @DisplayName("매출 항목 생성 테스트")
    @Transactional
    void create_sale_content_test() {
        // given
        Warehouse warehouse = warehouseService.create(EntitySnippet.warehouse());
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);
        TireDot tireDot = tireDotService.create(EntitySnippet.tireDot(), tire);
        Stock stock = stockService.createOrUpdate(EntitySnippet.stock(), tireDot, warehouse);
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);
        Sale sale = saleService.create(EntitySnippet.sale(), clientCompany);

        // when
        SaleContent create = saleContentService.create(EntitySnippet.saleContent(), sale, stock);

        // then
        assertNotNull(create.getId());
        assertEquals(create.getQuantity(), EntitySnippet.saleContent().getQuantity());
    }

    @Test
    @DisplayName("매출 항목 수정 테스트")
    @Transactional
    void update_sale_content_test() {
        // given
        Warehouse warehouse = warehouseService.create(EntitySnippet.warehouse());
        Brand brand = brandService.create(EntitySnippet.brand());
        Pattern pattern = patternService.create(EntitySnippet.pattern(), brand);
        Tire tire = tireService.create(EntitySnippet.tire(), pattern);
        TireDot tireDot = tireDotService.create(EntitySnippet.tireDot(), tire);
        Stock stock = stockService.createOrUpdate(EntitySnippet.stock(), tireDot, warehouse);
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);
        Sale sale = saleService.create(EntitySnippet.sale(), clientCompany);

        // when
        SaleContent create = saleContentService.create(EntitySnippet.saleContent(), sale, stock);
        SaleContent update = saleContentService.update(create.getId(), EntitySnippet.saleContent2(), stock);

        // then
        assertEquals(create.getId(), update.getId());
        assertEquals(create.getSale(), update.getSale());
        assertEquals(update.getQuantity(), EntitySnippet.saleContent2().getQuantity());
        assertNotEquals(update.getQuantity(), EntitySnippet.saleContent().getQuantity());
    }
}