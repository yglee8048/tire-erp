package com.minsoo.co.tireerp.domain.service.admin;

import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.constant.AccountRole;
import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.admin.Admin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @Test
    @DisplayName("관리자 생성 테스트")
    @Transactional
    void admin_create_test() {
        // when
        Admin admin = adminService.create(EntitySnippet.admin());

        // then
        assertNotNull(admin.getId());
    }

    @Test
    @DisplayName("관리자 아이디 중복 생성 테스트")
    @Transactional
    void admin_duplicate_username_create_test() {
        // when
        adminService.create(EntitySnippet.admin());

        // then
        assertThrows(BadRequestException.class, () -> adminService.create(EntitySnippet.admin()));
    }

    @Test
    @DisplayName("관리자 비정상 권한 생성 테스트")
    @Transactional
    void admin_invalid_role_create_test() {
        // then
        assertThrows(BadRequestException.class,
                () -> Admin.builder()
                        .role(AccountRole.CLIENT)
                        .username("test2")
                        .password("test")
                        .name("test_user")
                        .email("test@test.com")
                        .phoneNumber("010-1234-5678")
                        .description("테스트 용도 관리자")
                        .build());
    }

    @Test
    @DisplayName("관리자 수정 테스트")
    @Transactional
    void admin_update_test() {
        // given
        Admin create = adminService.create(EntitySnippet.admin());

        // when
        Admin update = adminService.update(create.getId(), EntitySnippet.admin2());

        // then
        assertEquals(update.getUsername(), EntitySnippet.admin2().getUsername());
        assertNotEquals(update.getUsername(), EntitySnippet.admin().getUsername());
    }

    @Test
    @DisplayName("관리자 아이디 중복 수정 테스트")
    @Transactional
    void admin_duplicate_username_update_test() {
        // when
        adminService.create(EntitySnippet.admin());
        Admin create = adminService.create(EntitySnippet.admin2());

        // then
        assertThrows(BadRequestException.class, () -> adminService.update(create.getId(), EntitySnippet.admin()));
    }
}