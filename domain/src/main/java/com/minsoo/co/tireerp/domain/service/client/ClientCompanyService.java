package com.minsoo.co.tireerp.domain.service.client;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.client.ClientCompany;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import com.minsoo.co.tireerp.domain.repository.client.ClientCompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ClientCompanyService {

    private final ClientCompanyRepository clientCompanyRepository;

    @Transactional(readOnly = true)
    public ClientCompany findById(Long id) {
        return clientCompanyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("client-company", id));
    }

    public ClientCompany create(ClientCompany create, Rank rank) {
        if (clientCompanyRepository.existsByName(create.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return clientCompanyRepository.save(create.setRank(rank));
    }

    public ClientCompany update(Long id, ClientCompany update, Rank rank) {
        ClientCompany found = findById(id);
        if (!found.getName().equals(update.getName()) && clientCompanyRepository.existsByName(update.getName())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_NAME);
        }
        return found.update(update, rank);
    }

    public void deleteById(Long id) {
        clientCompanyRepository.delete(findById(id));
    }
}
