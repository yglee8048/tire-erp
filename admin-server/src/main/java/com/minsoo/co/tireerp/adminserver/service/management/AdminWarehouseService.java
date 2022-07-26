package com.minsoo.co.tireerp.adminserver.service.management;

import com.minsoo.co.tireerp.adminserver.model.request.management.WarehouseRequest;
import com.minsoo.co.tireerp.adminserver.model.response.management.WarehouseResponse;
import com.minsoo.co.tireerp.domain.service.management.WarehouseService;
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
public class AdminWarehouseService {

    private final WarehouseService warehouseService;

    @Transactional(readOnly = true)
    public List<WarehouseResponse> findAll() {
        return warehouseService.findAll()
                .stream()
                .map(WarehouseResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public WarehouseResponse findById(Long id) {
        return WarehouseResponse.from(warehouseService.findById(id));
    }

    public WarehouseResponse create(WarehouseRequest request) {
        return WarehouseResponse.from(warehouseService.create(request.toEntity()));
    }

    public WarehouseResponse update(Long id, WarehouseRequest request) {
        return WarehouseResponse.from(warehouseService.update(id, request.toEntity()));
    }

    public void deleteById(Long id) {
        warehouseService.deleteById(id);
    }
}
