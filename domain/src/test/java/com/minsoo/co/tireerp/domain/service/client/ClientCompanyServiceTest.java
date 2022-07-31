package com.minsoo.co.tireerp.domain.service.client;

import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.client.ClientCompany;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import com.minsoo.co.tireerp.domain.service.rank.RankService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientCompanyServiceTest {

    @Autowired
    ClientCompanyService clientCompanyService;

    @Autowired
    RankService rankService;

    @Test
    @DisplayName("고객사 생성 테스트")
    @Transactional
    void client_company_create_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());

        // when
        ClientCompany create = clientCompanyService.create(EntitySnippet.clientCompany(), rank);

        // then
        assertNotNull(create.getId());
    }

    @Test
    @DisplayName("고객사 이름 중복 생성 테스트")
    @Transactional
    void client_company_name_duplicate_create_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());

        // when
        ClientCompany create = clientCompanyService.create(EntitySnippet.clientCompany(), rank);

        // then
        assertThrows(BadRequestException.class, () -> clientCompanyService.create(EntitySnippet.clientCompany(), rank));
    }

    @Test
    @DisplayName("고객사 수정 테스트")
    @Transactional
    void client_company_update_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());

        // when
        ClientCompany create = clientCompanyService.create(EntitySnippet.clientCompany(), rank);
        ClientCompany update = clientCompanyService.update(create.getId(), EntitySnippet.clientCompany2(), rank);

        // then
        assertEquals(create.getId(), update.getId());
        assertEquals(update.getName(), EntitySnippet.clientCompany2().getName());
        assertNotEquals(update.getName(), EntitySnippet.clientCompany().getName());
    }

    @Test
    @DisplayName("고객사 이름 중복 수정 테스트")
    @Transactional
    void client_company_name_duplicate_update_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());

        // when
        clientCompanyService.create(EntitySnippet.clientCompany(), rank);
        ClientCompany create = clientCompanyService.create(EntitySnippet.clientCompany2(), rank);

        // then
        assertThrows(BadRequestException.class, () -> clientCompanyService.update(create.getId(), EntitySnippet.clientCompany(), rank));
    }
}