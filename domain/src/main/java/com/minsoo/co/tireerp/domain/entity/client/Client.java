package com.minsoo.co.tireerp.domain.entity.client;

import com.minsoo.co.tireerp.core.constant.SystemMessage;
import com.minsoo.co.tireerp.core.exception.BadRequestException;
import com.minsoo.co.tireerp.domain.constant.AccountRole;
import com.minsoo.co.tireerp.domain.constant.AccountType;
import com.minsoo.co.tireerp.domain.entity.Address;
import com.minsoo.co.tireerp.domain.entity.account.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "client")
@DiscriminatorValue(AccountType.CLIENT)
public class Client extends Account {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_company_id", referencedColumnName = "client_company_id")
    private ClientCompany clientCompany;

    @Embedded
    private Address address;

    @Builder
    public Client(String username, String password, AccountRole role, String description, String name, String email, String phoneNumber, ClientCompany clientCompany, Address address) {
        super(null, username, password, role, description, name, email, phoneNumber);
        if (role.isAdmin()) {
            throw new BadRequestException(SystemMessage.INVALID_ROLE);
        }
        this.clientCompany = clientCompany;
        this.address = address;
    }

    public Client setClientCompany(ClientCompany clientCompany) {
        this.clientCompany = clientCompany;
        return this;
    }

    public Client update(Client update) {
        super.update(update);
        this.address = update.address;
        return this;
    }
}
