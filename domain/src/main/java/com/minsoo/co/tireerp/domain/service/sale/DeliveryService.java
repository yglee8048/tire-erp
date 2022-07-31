package com.minsoo.co.tireerp.domain.service.sale;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.sale.Delivery;
import com.minsoo.co.tireerp.domain.entity.sale.Sale;
import com.minsoo.co.tireerp.domain.repository.sale.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional(readOnly = true)
    public Delivery findById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("delivery", id));
    }

    public Delivery create(Delivery create, Sale sale) {
        if (deliveryRepository.existsBySale(sale)) {
            throw new BadRequestException(SystemMessage.ALREADY_EXIST);
        }
        return deliveryRepository.save(create.setSale(sale));
    }

    public Delivery update(Long id, Delivery update) {
        return findById(id)
                .update(update);
    }

    public void deleteById(Long id) {
        deliveryRepository.delete(findById(id));
    }
}
