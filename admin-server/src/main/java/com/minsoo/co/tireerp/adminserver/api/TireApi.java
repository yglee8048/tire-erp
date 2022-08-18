package com.minsoo.co.tireerp.adminserver.api;

import com.minsoo.co.tireerp.adminserver.model.ApiResponse;
import com.minsoo.co.tireerp.adminserver.model.request.tire.TireMemoRequest;
import com.minsoo.co.tireerp.adminserver.model.request.tire.TireRequest;
import com.minsoo.co.tireerp.adminserver.model.response.tire.TireDotGridResponse;
import com.minsoo.co.tireerp.adminserver.model.response.tire.TireGridResponse;
import com.minsoo.co.tireerp.adminserver.model.response.tire.TireMemoResponse;
import com.minsoo.co.tireerp.adminserver.model.response.tire.TireResponse;
import com.minsoo.co.tireerp.adminserver.service.tire.AdminTireDotService;
import com.minsoo.co.tireerp.adminserver.service.tire.AdminTireMemoService;
import com.minsoo.co.tireerp.adminserver.service.tire.AdminTireService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TireApi {

    private final AdminTireService adminTireService;
    private final AdminTireDotService adminTireDotService;
    private final AdminTireMemoService adminTireMemoService;

    @GetMapping("/tire-grids")
    public ApiResponse<List<TireGridResponse>> findAllTireGrids() {
        return ApiResponse.OK(adminTireService.findAllTireGrids());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tires")
    public ApiResponse<TireResponse> createTire(@RequestBody @Valid TireRequest tireRequest) {
        return ApiResponse.CREATED(adminTireService.create(tireRequest));
    }

    @GetMapping("/tires/{tireId}")
    public ApiResponse<TireResponse> findTireById(@PathVariable Long tireId) {
        return ApiResponse.OK(adminTireService.findById(tireId));
    }

    @PutMapping("/tires/{tireId}")
    public ApiResponse<TireResponse> updateTire(@PathVariable Long tireId,
                                                @RequestBody @Valid TireRequest tireRequest) {
        return ApiResponse.OK(adminTireService.update(tireId, tireRequest));
    }

    @DeleteMapping("/tires/{tireId}")
    public ApiResponse<Void> deleteTireById(@PathVariable Long tireId) {
        adminTireService.deleteById(tireId);
        return ApiResponse.NO_CONTENT();
    }

    @GetMapping("/tires/{tireId}/tire-dot-grids")
    public ApiResponse<List<TireDotGridResponse>> findTireDotGridsByTireId(@PathVariable Long tireId) {
        return ApiResponse.OK(adminTireDotService.findTireDotGridResponses(tireId));
    }

    @GetMapping("/tires/{tireId}/tire-memos")
    public ApiResponse<List<TireMemoResponse>> findAllTireMemos(@PathVariable Long tireId) {
        return ApiResponse.OK(adminTireMemoService.findAllByTireId(tireId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tires/{tireId}/tire-memos")
    public ApiResponse<TireMemoResponse> createTireMemo(@PathVariable Long tireId,
                                                        @RequestBody @Valid TireMemoRequest tireMemoRequest) {
        return ApiResponse.CREATED(adminTireMemoService.create(tireId, tireMemoRequest));
    }

    @PutMapping("/tires/{tireId}/tire-memos/{tireMemoId}")
    public ApiResponse<TireMemoResponse> updateTireMemo(@PathVariable Long tireId,
                                                        @PathVariable Long tireMemoId,
                                                        @RequestBody @Valid TireMemoRequest tireMemoRequest) {
        return ApiResponse.OK(adminTireMemoService.update(tireMemoId, tireMemoRequest));
    }

    @DeleteMapping("/tires/{tireId}/tire-memos/{tireMemoId}")
    public ApiResponse<Void> deleteTireMemoById(@PathVariable Long tireId,
                                                @PathVariable Long tireMemoId) {
        adminTireMemoService.deleteById(tireMemoId);
        return ApiResponse.NO_CONTENT();
    }
}
