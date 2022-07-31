package com.minsoo.co.tireerp.domain.service.client;

import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.constant.AccountRole;
import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.client.Client;
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
class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientCompanyService clientCompanyService;

    @Autowired
    RankService rankService;

    @Test
    @DisplayName("고객 생성 테스트")
    @Transactional
    void client_create_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);

        // when
        Client create = clientService.create(EntitySnippet.client(), clientCompany);

        // then
        assertNotNull(create.getId());
    }

    @Test
    @DisplayName("고객 아이디 중복 생성 테스트")
    @Transactional
    void client_username_duplicate_create_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);

        // when
        clientService.create(EntitySnippet.client(), clientCompany);

        // then
        assertThrows(BadRequestException.class, () -> clientService.create(EntitySnippet.client(), clientCompany));
    }

    @Test
    @DisplayName("고객 비정상 권한 생성 테스트")
    @Transactional
    void client_invalid_role_create_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);

        // then
        assertThrows(BadRequestException.class, () -> clientService.create(
                Client.builder()
                        .clientCompany(null)
                        .role(AccountRole.ROOT)
                        .username("test")
                        .password("test")
                        .name("test_user")
                        .email("test@test.com")
                        .phoneNumber("010-1234-5678")
                        .description("테스트 용도 고객")
                        .address(EntitySnippet.address())
                        .build(), clientCompany));
    }

    @Test
    @DisplayName("고객 수정 테스트")
    @Transactional
    void client_update_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);

        // when
        Client create = clientService.create(EntitySnippet.client(), clientCompany);
        Client update = clientService.update(create.getId(), EntitySnippet.client2());

        // then
        assertEquals(create.getId(), update.getId());
        assertEquals(update.getUsername(), EntitySnippet.client2().getUsername());
        assertNotEquals(update.getUsername(), EntitySnippet.client().getUsername());
    }

    @Test
    @DisplayName("고객 아이디 중복 수정 테스트")
    @Transactional
    void client_username_duplicate_update_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);

        // when
        clientService.create(EntitySnippet.client(), clientCompany);
        Client create = clientService.create(EntitySnippet.client2(), clientCompany);

        // then
        assertThrows(BadRequestException.class, () -> clientService.update(create.getId(), EntitySnippet.client()));
    }

    @Test
    @DisplayName("고객 비정상 권한 수정 테스트")
    @Transactional
    void client_invalid_role_update_test() {
        // given
        Rank rank = rankService.create(EntitySnippet.rank());
        ClientCompany clientCompany = clientCompanyService.create(EntitySnippet.clientCompany(), rank);

        // when
        Client create = clientService.create(EntitySnippet.client(), clientCompany);

        // then
        assertThrows(BadRequestException.class, () -> clientService.update(create.getId(), Client.builder()
                .clientCompany(null)
                .role(AccountRole.ROOT)
                .username("test")
                .password("test")
                .name("test_user")
                .email("test@test.com")
                .phoneNumber("010-1234-5678")
                .description("테스트 용도 고객")
                .address(EntitySnippet.address())
                .build()));
    }
}