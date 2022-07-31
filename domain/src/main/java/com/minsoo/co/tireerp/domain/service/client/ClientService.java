package com.minsoo.co.tireerp.domain.service.client;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.client.Client;
import com.minsoo.co.tireerp.domain.entity.client.ClientCompany;
import com.minsoo.co.tireerp.domain.repository.account.AccountRepository;
import com.minsoo.co.tireerp.domain.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("client", id));
    }

    public Client create(Client create, ClientCompany clientCompany) {
        if (accountRepository.existsByUsername(create.getUsername())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_USERNAME);
        }
        return clientRepository.save(create.setClientCompany(clientCompany));
    }

    public Client update(Long id, Client update) {
        Client found = findById(id);
        if (!found.getUsername().equals(update.getUsername()) && accountRepository.existsByUsername(update.getUsername())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_USERNAME);
        }
        return found.update(update);
    }

    public void deleteById(Long id) {
        clientRepository.delete(findById(id));
    }
}
