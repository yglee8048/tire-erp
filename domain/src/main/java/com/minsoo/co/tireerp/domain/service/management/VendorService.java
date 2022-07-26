package com.minsoo.co.tireerp.domain.service.management;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.management.Vendor;
import com.minsoo.co.tireerp.domain.repository.management.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;

    @Transactional(readOnly = true)
    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Vendor findById(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("vendor", id));
    }

    public Vendor create(Vendor create) {
        if (vendorRepository.existsByName(create.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return vendorRepository.save(create);
    }

    public Vendor update(Long id, Vendor update) {
        Vendor vendor = findById(id);
        if (!vendor.getName().equals(update.getName()) && vendorRepository.existsByName(update.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return vendor.update(update);
    }

    public void deleteById(Long id) {
        vendorRepository.delete(findById(id));
    }
}
