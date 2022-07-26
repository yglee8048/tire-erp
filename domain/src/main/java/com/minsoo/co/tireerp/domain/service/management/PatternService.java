package com.minsoo.co.tireerp.domain.service.management;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.repository.management.PatternRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PatternService {

    private final PatternRepository patternRepository;
    private final BrandService brandService;

    @Transactional(readOnly = true)
    public List<Pattern> findAllByBrandId(Long brandId) {
        return patternRepository.findAllByBrand(brandService.findById(brandId));
    }

    @Transactional(readOnly = true)
    public Pattern findById(Long id) {
        return patternRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("pattern", id));
    }

    public Pattern create(Pattern create, Brand brand) {
        if (patternRepository.existsByBrandAndName(brand, create.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return patternRepository.save(create.setBrand(brand));
    }

    public Pattern update(Long id, Pattern update, Brand brand) {
        Pattern pattern = findById(id);
        if (!pattern.getName().equals(update.getName()) && patternRepository.existsByBrandAndName(brand, update.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return pattern.update(update, brand);
    }

    public void deleteById(Long id) {
        patternRepository.delete(findById(id));
    }
}
