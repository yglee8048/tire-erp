package com.minsoo.co.tireerp.domain.service.rank;

import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.entity.EntitySnippet;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RankServiceTest {

    @Autowired
    RankService rankService;

    @Test
    @DisplayName("등급 생성 테스트")
    @Transactional
    void rank_create_test() {
        // when
        Rank create = rankService.create(EntitySnippet.rank());

        // then
        assertNotNull(create.getId());
    }

    @Test
    @DisplayName("등급 이름 중복 생성 테스트")
    @Transactional
    void rank_duplicate_name_create_test() {
        // when
        rankService.create(EntitySnippet.rank());

        // then
        assertThrows(BadRequestException.class, () -> rankService.create(EntitySnippet.rank()));
    }

    @Test
    @DisplayName("등급 수정 테스트")
    @Transactional
    void rank_update_test() {
        // given
        Rank create = rankService.create(EntitySnippet.rank());

        // when
        Rank update = rankService.update(create.getId(), EntitySnippet.rank2());

        // then
        assertEquals(update.getName(), EntitySnippet.rank2().getName());
        assertNotEquals(update.getName(), EntitySnippet.rank().getName());
    }

    @Test
    @DisplayName("등급 이름 중복 수정 테스트")
    @Transactional
    void rank_duplicate_name_update_test() {
        // given
        rankService.create(EntitySnippet.rank());
        Rank create = rankService.create(EntitySnippet.rank2());

        // then
        assertThrows(BadRequestException.class, () -> rankService.update(create.getId(), EntitySnippet.rank()));
    }
}