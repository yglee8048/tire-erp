package com.minsoo.co.tireerp.adminserver.service.management;

import com.minsoo.co.tireerp.adminserver.model.request.management.BrandRequest;
import com.minsoo.co.tireerp.adminserver.model.response.management.BrandResponse;
import com.minsoo.co.tireerp.domain.service.management.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminBrandService {

    private final BrandService brandService;

    @Transactional(readOnly = true)
    public List<BrandResponse> findAll() {
        return brandService.findAll()
                .stream()
                .map(BrandResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BrandResponse findById(Long id) {
        return BrandResponse.from(brandService.findById(id));
    }

    public BrandResponse create(BrandRequest request) {
        return BrandResponse.from(brandService.create(request.toEntity()));
    }

    public BrandResponse update(Long id, BrandRequest request) {
        return BrandResponse.from(brandService.update(id, request.toEntity()));
    }

    public void deleteById(Long id) {
        brandService.deleteById(id);
    }
}
