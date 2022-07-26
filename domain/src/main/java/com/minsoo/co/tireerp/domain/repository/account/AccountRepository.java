package com.minsoo.co.tireerp.domain.repository.account;

import com.minsoo.co.tireerp.domain.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
