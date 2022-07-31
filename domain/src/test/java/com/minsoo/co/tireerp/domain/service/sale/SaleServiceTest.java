package com.minsoo.co.tireerp.domain.service.sale;

import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.client.ClientCompany;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import com.minsoo.co.tireerp.domain.entity.sale.Delivery;
import com.minsoo.co.tireerp.domain.entity.sale.Sale;
import com.minsoo.co.tireerp.domain.service.client.ClientCompanyService;
import com.minsoo.co.tireerp.domain.service.rank.RankService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SaleServiceTest {

    @Autowired
    SaleService saleService;

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    ClientCompanyService clientCompanyService;

    @Autowired
    RankService rankService;

    @Test
    @DisplayName("매출 생성 테스트")
    @Transactional
    void sale_create_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);

        // when
        Sale create = saleService.create(EntitySnippet.sale(), clientCompany);

        // then
        assertNotNull(create.getId());
    }

    @Test
    @DisplayName("매출 수정 테스트")
    @Transactional
    void sale_update_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);
        Sale create = saleService.create(EntitySnippet.sale(), clientCompany);

        // when
        Delivery delivery = deliveryService.create(EntitySnippet.delivery(), create);
        Sale update = saleService.update(create.getId(), EntitySnippet.sale2(), clientCompany, delivery);

        // then
        assertNotNull(update.getDelivery());
        assertEquals(update.getSource(), EntitySnippet.sale2().getSource());
    }
}