package com.minsoo.co.tireerp.domain.service.admin;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.core.exception.NotFoundException;
import com.minsoo.co.tireerp.domain.entity.admin.Admin;
import com.minsoo.co.tireerp.domain.repository.account.AccountRepository;
import com.minsoo.co.tireerp.domain.repository.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Admin findById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("admin", id));
    }

    public Admin create(Admin admin) {
        if (accountRepository.existsByUsername(admin.getUsername())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_USERNAME);
        }
        return adminRepository.save(admin);
    }

    public Admin update(Long id, Admin update) {
        Admin found = findById(id);
        if (!found.getUsername().equals(update.getUsername()) && accountRepository.existsByUsername(update.getUsername())) {
            throw new BadRequestException(SystemMessage.DUPLICATED_USERNAME);
        }
        return found.update(update);
    }

    public void deleteById(Long id) {
        adminRepository.delete(findById(id));
    }
}
