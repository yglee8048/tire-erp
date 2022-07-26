package com.minsoo.co.tireerp.adminserver.service.management;

import com.minsoo.co.tireerp.adminserver.model.request.management.PatternRequest;
import com.minsoo.co.tireerp.adminserver.model.response.management.PatternResponse;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.service.management.BrandService;
import com.minsoo.co.tireerp.domain.service.management.PatternService;
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
public class AdminPatternService {

    private final BrandService brandService;
    private final PatternService patternService;

    @Transactional(readOnly = true)
    public List<PatternResponse> findAllByBrandId(Long brandId) {
        return patternService.findAllByBrandId(brandId)
                .stream()
                .map(PatternResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PatternResponse findById(Long id) {
        return PatternResponse.from(patternService.findById(id));
    }

    public PatternResponse create(Long brandId, PatternRequest patternRequest) {
        Brand brand = brandService.findById(brandId);
        return PatternResponse.from(patternService.create(patternRequest.toEntity(), brand));
    }

    public PatternResponse update(Long brandId, Long patternId, PatternRequest patternRequest) {
        Brand brand = brandService.findById(brandId);
        return PatternResponse.from(patternService.update(patternId, patternRequest.toEntity(), brand));
    }

    public void deleteById(Long id) {
        patternService.deleteById(id);
    }
}
