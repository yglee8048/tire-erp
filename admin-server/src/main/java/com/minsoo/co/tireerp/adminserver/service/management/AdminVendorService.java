package com.minsoo.co.tireerp.adminserver.service.management;

import com.minsoo.co.tireerp.adminserver.model.request.management.VendorRequest;
import com.minsoo.co.tireerp.adminserver.model.response.management.VendorResponse;
import com.minsoo.co.tireerp.domain.service.management.VendorService;
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
public class AdminVendorService {

    private final VendorService vendorService;

    @Transactional(readOnly = true)
    public List<VendorResponse> findAll() {
        return vendorService.findAll()
                .stream()
                .map(VendorResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VendorResponse findById(Long id) {
        return VendorResponse.from(vendorService.findById(id));
    }

    public VendorResponse create(VendorRequest request) {
        return VendorResponse.from(vendorService.create(request.toEntity()));
    }

    public VendorResponse update(Long id, VendorRequest request) {
        return VendorResponse.from(vendorService.update(id, request.toEntity()));
    }

    public void deleteById(Long id) {
        vendorService.deleteById(id);
    }
}
