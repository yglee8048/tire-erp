package com.minsoo.co.tireerp.domain.service.sale;

import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.sale.Sale;
import com.minsoo.co.tireerp.domain.entity.sale.SaleMemo;
import com.minsoo.co.tireerp.domain.repository.sale.SaleMemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SaleMemoService {

    private final SaleMemoRepository saleMemoRepository;

    @Transactional(readOnly = true)
    public SaleMemo findById(Long id) {
        return saleMemoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("sale-memo", id));
    }

    public SaleMemo create(SaleMemo create, Sale sale) {
        return saleMemoRepository.save(create.setSale(sale));
    }

    public SaleMemo update(Long id, SaleMemo update) {
        return findById(id)
                .update(update);
    }

    public void deleteById(Long id) {
        saleMemoRepository.delete(findById(id));
    }
}
