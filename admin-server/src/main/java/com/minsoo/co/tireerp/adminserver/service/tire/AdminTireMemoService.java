package com.minsoo.co.tireerp.adminserver.service.tire;

import com.minsoo.co.tireerp.adminserver.model.request.tire.TireMemoRequest;
import com.minsoo.co.tireerp.adminserver.model.response.tire.TireMemoResponse;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.service.tire.TireMemoService;
import com.minsoo.co.tireerp.domain.service.tire.TireService;
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
public class AdminTireMemoService {

    private final TireService tireService;
    private final TireMemoService tireMemoService;

    @Transactional(readOnly = true)
    public List<TireMemoResponse> findAllByTireId(Long tireId) {
        Tire tire = tireService.findById(tireId);
        return tireMemoService.findAllByTire(tire)
                .stream()
                .map(TireMemoResponse::from)
                .collect(Collectors.toList());
    }

    public TireMemoResponse create(Long tireId, TireMemoRequest tireMemoRequest) {
        Tire tire = tireService.findById(tireId);
        return TireMemoResponse.from(tireMemoService.create(tireMemoRequest.toEntity(), tire));
    }

    public TireMemoResponse update(Long tireMemoId, TireMemoRequest tireMemoRequest) {
        return TireMemoResponse.from(tireMemoService.update(tireMemoId, tireMemoRequest.toEntity()));
    }

    public void deleteById(Long id) {
        tireMemoService.deleteById(id);
    }
}
