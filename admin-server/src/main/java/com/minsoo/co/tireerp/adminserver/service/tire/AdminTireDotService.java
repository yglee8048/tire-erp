package com.minsoo.co.tireerp.adminserver.service.tire;

import com.minsoo.co.tireerp.adminserver.model.response.tire.TireDotGridResponse;
import com.minsoo.co.tireerp.adminserver.repository.tire.TireDotGridRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminTireDotService {

    private final TireDotGridRepository tireDotGridRepository;

    @Transactional(readOnly = true)
    public List<TireDotGridResponse> findTireDotGridResponses(Long tireId) {
        return tireDotGridRepository.getTireDotGridResponsesByTireId(tireId);
    }
}
