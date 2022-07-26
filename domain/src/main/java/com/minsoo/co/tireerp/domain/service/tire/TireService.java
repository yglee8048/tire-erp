package com.minsoo.co.tireerp.domain.service.tire;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.repository.tire.TireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TireService {

    private final TireRepository tireRepository;

    @Transactional(readOnly = true)
    public Tire findById(Long id) {
        return tireRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("tire", id));
    }

    public Tire create(Tire create, Pattern pattern) {
        if (tireRepository.existsByTireCode(create.getTireCode())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_TIRE_CODE);
        }
        return tireRepository.save(create.setPattern(pattern));
    }

    public Tire update(Long id, Tire update, Pattern pattern) {
        Tire tire = findById(id);
        if (!tire.getTireCode().equals(update.getTireCode()) && tireRepository.existsByTireCode(update.getTireCode())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_TIRE_CODE);
        }
        return tire.update(update, pattern);
    }

    public void deleteById(Long id) {
        tireRepository.delete(findById(id));
    }
}
