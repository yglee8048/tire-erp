package com.minsoo.co.tireerp.domain.service.sale;

import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.client.ClientCompany;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import com.minsoo.co.tireerp.domain.entity.sale.Sale;
import com.minsoo.co.tireerp.domain.entity.sale.SaleMemo;
import com.minsoo.co.tireerp.domain.service.client.ClientCompanyService;
import com.minsoo.co.tireerp.domain.service.rank.RankService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SaleMemoServiceTest {

    @Autowired
    SaleMemoService saleMemoService;

    @Autowired
    SaleService saleService;

    @Autowired
    ClientCompanyService clientCompanyService;

    @Autowired
    RankService rankService;

    @Test
    @DisplayName("매출 메모 생성 테스트")
    @Transactional
    void sale_memo_create_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);
        Sale sale = saleService.create(EntitySnippet.sale(), clientCompany);

        // when
        SaleMemo create = saleMemoService.create(EntitySnippet.saleMemo(), sale);

        // then
        assertNotNull(create.getId());
        assertEquals(create.getSale(), sale);
    }

    @Test
    @DisplayName("매출 메모 수정 테스트")
    @Transactional
    void sale_memo_update_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);
        Sale sale = saleService.create(EntitySnippet.sale(), clientCompany);

        // when
        SaleMemo create = saleMemoService.create(EntitySnippet.saleMemo(), sale);
        SaleMemo update = saleMemoService.update(create.getId(), EntitySnippet.saleMemo2());

        // then
        assertEquals(create.getId(), update.getId());
        assertEquals(update.getMemo(), EntitySnippet.saleMemo2().getMemo());
        assertNotEquals(update.getMemo(), EntitySnippet.saleMemo().getMemo());
    }
}