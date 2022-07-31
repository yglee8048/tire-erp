package com.minsoo.co.tireerp.domain.entity.client;

import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import com.minsoo.co.tireerp.domain.entity.BusinessInfo;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "client_company")
public class ClientCompany extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_company_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id", referencedColumnName = "rank_id")
    private Rank rank;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Embedded
    private BusinessInfo businessInfo;

    public ClientCompany setRank(Rank rank) {
        this.rank = rank;
        return this;
    }

    public ClientCompany update(ClientCompany update, Rank rank) {
        this.rank = rank;
        this.name = update.name;
        this.description = update.description;
        this.businessInfo = update.businessInfo;
        return this;
    }
}
