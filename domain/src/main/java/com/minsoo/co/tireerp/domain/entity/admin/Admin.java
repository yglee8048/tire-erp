package com.minsoo.co.tireerp.domain.entity.admin;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.constant.AccountRole;
import com.minsoo.co.tireerp.domain.constant.AccountType;
import com.minsoo.co.tireerp.domain.entity.account.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "admin")
@DiscriminatorValue(AccountType.ADMIN)
public class Admin extends Account {

    @Builder
    public Admin(String username, String password, AccountRole role, String description, String name, String email, String phoneNumber) {
        super(null, username, password, role, description, name, email, phoneNumber);
        if (!role.isAdmin()) {
            throw new BadRequestException(SystemMessage.INVALID_ROLE);
        }
    }

    public Admin update(Admin update) {
        super.update(update);
        return this;
    }
}
