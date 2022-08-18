package com.minsoo.co.tireerp.adminserver.service.tire;

import com.minsoo.co.tireerp.adminserver.model.request.tire.TireRequest;
import com.minsoo.co.tireerp.adminserver.model.response.tire.TireGridResponse;
import com.minsoo.co.tireerp.adminserver.model.response.tire.TireResponse;
import com.minsoo.co.tireerp.adminserver.repository.tire.TireGridRepository;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.service.management.PatternService;
import com.minsoo.co.tireerp.domain.service.tire.TireService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminTireService {

    private final TireService tireService;
    private final PatternService patternService;
    private final TireGridRepository tireGridRepository;

    @Transactional(readOnly = true)
    public List<TireGridResponse> findAllTireGrids() {
        return tireGridRepository.getTireGridResponses();
    }

    @Transactional(readOnly = true)
    public TireResponse findById(Long id) {
        return TireResponse.from(tireService.findById(id));
    }

    public TireResponse create(TireRequest tireRequest) {
        Pattern pattern = patternService.findById(tireRequest.getPatternId());
        return TireResponse.from(tireService.create(tireRequest.toEntity(), pattern));
    }

    public TireResponse update(Long id, TireRequest tireRequest) {
        Pattern pattern = patternService.findById(tireRequest.getPatternId());
        return TireResponse.from(tireService.update(id, tireRequest.toEntity(), pattern));
    }

    public void deleteById(Long id) {
        tireService.deleteById(id);
    }
}
