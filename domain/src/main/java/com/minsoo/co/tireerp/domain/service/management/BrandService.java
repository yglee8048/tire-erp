package com.minsoo.co.tireerp.domain.service.management;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.repository.management.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    @Transactional(readOnly = true)
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Brand findById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Brand.class.getSimpleName(), id));
    }

    public Brand create(Brand create) {
        if (brandRepository.existsByName(create.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return brandRepository.save(create);
    }

    public Brand update(Long id, Brand update) {
        Brand brand = findById(id);
        if (!brand.getName().equals(update.getName()) && brandRepository.existsByName(update.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return brand.update(update);
    }

    public void deleteById(Long id) {
        brandRepository.delete(findById(id));
    }
}
