package com.minsoo.co.tireerp.adminserver.service.tire;

import com.minsoo.co.tireerp.adminserver.model.ModelSnippet;
import com.minsoo.co.tireerp.adminserver.model.request.tire.TireRequest;
import com.minsoo.co.tireerp.adminserver.service.management.AdminBrandService;
import com.minsoo.co.tireerp.adminserver.service.management.AdminPatternService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminTireServiceTest {

    @Autowired
    AdminTireService adminTireService;

    @Autowired
    AdminPatternService adminPatternService;

    @Autowired
    AdminBrandService adminBrandService;

    @DisplayName("tire grid 조회 테스트")
    void tire_grid_test() {



        adminTireService.create(ModelSnippet.tireRequest());

        adminTireService.findAllTireGrids();
    }

    @DisplayName("tire-dot grid 조회 테스트")
    void tire_dot_grid_test() {

    }
}