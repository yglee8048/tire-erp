package com.minsoo.co.tireerp.domain.service.rank;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import com.minsoo.co.tireerp.domain.repository.rank.RankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RankService {

    private final RankRepository rankRepository;

    @Transactional(readOnly = true)
    public Rank findById(Long id) {
        return rankRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("rank", id));
    }

    public Rank create(Rank create) {
        if (rankRepository.existsByName(create.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return rankRepository.save(create);
    }

    public Rank update(Long id, Rank update) {
        Rank found = findById(id);
        if (!found.getName().equals(update.getName()) && rankRepository.existsByName(update.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return found.update(update);
    }

    public void deleteById(Long id) {
        rankRepository.delete(findById(id));
    }
}
