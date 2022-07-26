package com.minsoo.co.tireerp.domain.service.tire;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.minsoo.co.tireerp.domain.repository.tire.TireDotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TireDotService {

    private final TireDotRepository tireDotRepository;

    @Transactional(readOnly = true)
    public TireDot findById(Long id) {
        return tireDotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("tire-dot", id));
    }

    public TireDot create(TireDot create, Tire tire) {
        if (tireDotRepository.existsByTireAndDot(tire, create.getDot())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_TIRE_DOT);
        }
        return tireDotRepository.save(create.setTire(tire));
    }

    public TireDot update(Long id, TireDot update, Tire tire) {
        TireDot tireDot = findById(id);
        if (!(tireDot.getDot().equals(update.getDot()) && tireDot.getTire().getId().equals(tire.getId()))
                && tireDotRepository.existsByTireAndDot(tire, update.getDot())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_TIRE_DOT);
        }
        return tireDot.update(update, tire);
    }

    public void deleteById(Long id) {
        tireDotRepository.delete(findById(id));
    }
}
