package com.minsoo.co.tireerp.domain.service.sale;

import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.client.ClientCompany;
import com.minsoo.co.tireerp.domain.entity.sale.Delivery;
import com.minsoo.co.tireerp.domain.entity.sale.Sale;
import com.minsoo.co.tireerp.domain.repository.sale.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    @Transactional(readOnly = true)
    public Sale findById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("sale", id));
    }

    public Sale create(Sale create, ClientCompany clientCompany) {
        return saleRepository.save(create
                .setClientCompany(clientCompany));
    }

    public Sale update(Long id, Sale update, ClientCompany clientCompany, Delivery deliveryUpdate) {
        return findById(id)
                .update(update, clientCompany, deliveryUpdate);
    }

    public void deleteById(Long id) {
        saleRepository.delete(findById(id));
    }
}
