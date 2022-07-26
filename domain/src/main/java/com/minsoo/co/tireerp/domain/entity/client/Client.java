package com.minsoo.co.tireerp.domain.entity.client;

import com.minsoo.co.tireerp.domain.constant.AccountType;
import com.minsoo.co.tireerp.domain.entity.Address;
import com.minsoo.co.tireerp.domain.entity.account.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "client")
@DiscriminatorValue(AccountType.CLIENT)
public class Client extends Account {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_company_id", referencedColumnName = "client_company_id")
    private ClientCompany clientCompany;

    @Embedded
    private Address address;
}
