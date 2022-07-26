package com.minsoo.co.tireerp.domain.service.tire;

import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireMemo;
import com.minsoo.co.tireerp.domain.repository.tire.TireMemoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TireMemoService {

    private final TireMemoRepository tireMemoRepository;

    @Transactional(readOnly = true)
    public TireMemo findById(Long id) {
        return tireMemoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("tire-memo", id));
    }

    public TireMemo create(TireMemo create, Tire tire) {
        return tireMemoRepository.save(create.setTire(tire));
    }

    public TireMemo update(Long id, TireMemo update, Tire tire) {
        return findById(id)
                .update(update, tire);
    }

    public void deleteById(Long id) {
        tireMemoRepository.delete(findById(id));
    }
}
