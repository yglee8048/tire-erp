package com.minsoo.co.tireerp.adminserver.api;

import com.minsoo.co.tireerp.adminserver.model.ApiResponse;
import com.minsoo.co.tireerp.adminserver.model.request.management.BrandRequest;
import com.minsoo.co.tireerp.adminserver.model.request.management.PatternRequest;
import com.minsoo.co.tireerp.adminserver.model.request.management.VendorRequest;
import com.minsoo.co.tireerp.adminserver.model.request.management.WarehouseRequest;
import com.minsoo.co.tireerp.adminserver.model.response.management.BrandResponse;
import com.minsoo.co.tireerp.adminserver.model.response.management.PatternResponse;
import com.minsoo.co.tireerp.adminserver.model.response.management.VendorResponse;
import com.minsoo.co.tireerp.adminserver.model.response.management.WarehouseResponse;
import com.minsoo.co.tireerp.adminserver.service.management.AdminBrandService;
import com.minsoo.co.tireerp.adminserver.service.management.AdminPatternService;
import com.minsoo.co.tireerp.adminserver.service.management.AdminVendorService;
import com.minsoo.co.tireerp.adminserver.service.management.AdminWarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ManagementApi {

    private final AdminBrandService adminBrandService;
    private final AdminPatternService adminPatternService;
    private final AdminVendorService adminVendorService;
    private final AdminWarehouseService adminWarehouseService;

    @GetMapping("/brands")
    public ApiResponse<List<BrandResponse>> findAllBrand() {
        return ApiResponse.OK(adminBrandService.findAll());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/brands")
    public ApiResponse<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest) {
        return ApiResponse.CREATED(adminBrandService.create(brandRequest));
    }

    @GetMapping("/brands/{brandId}")
    public ApiResponse<BrandResponse> findBrandById(@PathVariable Long brandId) {
        return ApiResponse.OK(adminBrandService.findById(brandId));
    }

    @PutMapping("/brands/{brandId}")
    public ApiResponse<BrandResponse> updateBrand(@PathVariable Long brandId,
                                                  @RequestBody @Valid BrandRequest brandRequest) {
        return ApiResponse.OK(adminBrandService.update(brandId, brandRequest));
    }

    @DeleteMapping("/brands/{brandId}")
    public ApiResponse<Void> deleteBrand(@PathVariable Long brandId) {
        adminBrandService.deleteById(brandId);
        return ApiResponse.NO_CONTENT();
    }

    @GetMapping("/brands/{brandId}/patterns")
    public ApiResponse<List<PatternResponse>> findAllPatterns(@PathVariable Long brandId) {
        return ApiResponse.OK(adminPatternService.findAllByBrandId(brandId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/brands/{brandId}/patterns")
    public ApiResponse<PatternResponse> createPattern(@PathVariable Long brandId,
                                                      @RequestBody @Valid PatternRequest patternRequest) {
        return ApiResponse.CREATED(adminPatternService.create(brandId, patternRequest));
    }

    @GetMapping("/brands/{brandId}/patterns/{patternId}")
    public ApiResponse<PatternResponse> findPatternById(@PathVariable Long brandId,
                                                        @PathVariable Long patternId) {
        return ApiResponse.OK(adminPatternService.findById(patternId));
    }

    @PutMapping("/brands/{brandId}/patterns/{patternId}")
    public ApiResponse<PatternResponse> updatePattern(@PathVariable Long brandId,
                                                      @PathVariable Long patternId,
                                                      @RequestBody @Valid PatternRequest patternRequest) {
        return ApiResponse.OK(adminPatternService.update(brandId, patternId, patternRequest));
    }

    @DeleteMapping("/brands/{brandId}/patterns/{patternId}")
    public ApiResponse<Void> deletePatternById(@PathVariable Long brandId,
                                               @PathVariable Long patternId) {
        adminPatternService.deleteById(patternId);
        return ApiResponse.NO_CONTENT();
    }

    @GetMapping("/vendors")
    public ApiResponse<List<VendorResponse>> findAllVendors() {
        return ApiResponse.OK(adminVendorService.findAll());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/vendors")
    public ApiResponse<VendorResponse> createVendor(@RequestBody @Valid VendorRequest vendorRequest) {
        return ApiResponse.CREATED(adminVendorService.create(vendorRequest));
    }

    @GetMapping("/vendors/{vendorId}")
    public ApiResponse<VendorResponse> findVendorById(@PathVariable Long vendorId) {
        return ApiResponse.OK(adminVendorService.findById(vendorId));
    }

    @PutMapping("/vendors/{vendorId}")
    public ApiResponse<VendorResponse> updateVendor(@PathVariable Long vendorId,
                                                    @RequestBody @Valid VendorRequest vendorRequest) {
        return ApiResponse.OK(adminVendorService.update(vendorId, vendorRequest));
    }

    @DeleteMapping("/vendors/{vendorId}")
    public ApiResponse<Void> deleteVendorById(@PathVariable Long vendorId) {
        adminVendorService.deleteById(vendorId);
        return ApiResponse.NO_CONTENT();
    }

    @GetMapping("/warehouses")
    public ApiResponse<List<WarehouseResponse>> findAllWarehouses() {
        return ApiResponse.OK(adminWarehouseService.findAll());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/warehouses")
    public ApiResponse<WarehouseResponse> createWarehouse(@RequestBody @Valid WarehouseRequest warehouseRequest) {
        return ApiResponse.CREATED(adminWarehouseService.create(warehouseRequest));
    }

    @GetMapping("/warehouses/{warehouseId}")
    public ApiResponse<WarehouseResponse> findWarehouseById(@PathVariable Long warehouseId) {
        return ApiResponse.OK(adminWarehouseService.findById(warehouseId));
    }

    @PutMapping("/warehouses/{warehouseId}")
    public ApiResponse<WarehouseResponse> updateWarehouse(@PathVariable Long warehouseId,
                                                          @RequestBody @Valid WarehouseRequest warehouseRequest) {
        return ApiResponse.OK(adminWarehouseService.update(warehouseId, warehouseRequest));
    }

    @DeleteMapping("/warehouses/{warehouseId}")
    public ApiResponse<Void> deleteWarehouseById(@PathVariable Long warehouseId) {
        adminWarehouseService.deleteById(warehouseId);
        return ApiResponse.NO_CONTENT();
    }
}
