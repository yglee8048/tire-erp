package com.minsoo.co.tireerp.domain.service.management;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import com.minsoo.co.tireerp.domain.repository.management.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Transactional(readOnly = true)
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Warehouse findById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("warehouse", id));
    }

    public Warehouse create(Warehouse create) {
        if (warehouseRepository.existsByName(create.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return warehouseRepository.save(create);
    }

    public Warehouse update(Long id, Warehouse update) {
        Warehouse warehouse = findById(id);
        if (!warehouse.getName().equals(update.getName()) && warehouseRepository.existsByName(update.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return warehouse.update(update);
    }

    public void deleteById(Long id) {
        warehouseRepository.delete(findById(id));
    }
}
