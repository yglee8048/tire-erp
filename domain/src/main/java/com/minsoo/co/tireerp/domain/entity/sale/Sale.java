package com.minsoo.co.tireerp.domain.entity.sale;

import com.minsoo.co.tireerp.domain.constant.SaleSource;
import com.minsoo.co.tireerp.domain.constant.SaleStatus;
import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import com.minsoo.co.tireerp.domain.entity.client.ClientCompany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "sale")
public class Sale extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_company_id", referencedColumnName = "client_company_id")
    private ClientCompany clientCompany;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    @Column(name = "source")
    private SaleSource source;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SaleStatus status;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "desired_delivery_date")
    private LocalDate desiredDeliveryDate;

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<SaleContent> saleContents = new HashSet<>();

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<SaleMemo> saleMemos = new HashSet<>();

    @Builder
    public Sale(ClientCompany clientCompany, SaleSource source, LocalDate transactionDate, LocalDate releaseDate, LocalDate desiredDeliveryDate) {
        this.clientCompany = clientCompany;
        this.source = source;
        this.status = SaleStatus.REQUESTED;
        this.transactionDate = transactionDate;
        this.releaseDate = releaseDate;
        this.desiredDeliveryDate = desiredDeliveryDate;
    }

    public Sale setClientCompany(ClientCompany clientCompany) {
        this.clientCompany = clientCompany;
        this.delivery = new Delivery(clientCompany);
        return this;
    }

    public Sale update(Sale update, ClientCompany clientCompany, Delivery deliveryUpdate) {
        this.clientCompany = clientCompany;
        this.delivery.update(deliveryUpdate);
        this.source = update.source;
        this.status = update.status;
        this.transactionDate = update.transactionDate;
        this.releaseDate = update.releaseDate;
        this.desiredDeliveryDate = update.desiredDeliveryDate;
        return this;
    }
}
