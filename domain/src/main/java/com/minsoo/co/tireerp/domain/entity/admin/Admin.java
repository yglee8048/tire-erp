package com.minsoo.co.tireerp.domain.entity.admin;

import com.minsoo.co.tireerp.domain.constant.AccountType;
import com.minsoo.co.tireerp.domain.entity.account.Account;
import lombok.AccessLevel;
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
}
