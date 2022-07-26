package com.minsoo.co.tireerp.domain.entity.sale;

import com.minsoo.co.tireerp.domain.constant.DeliveryOption;
import com.minsoo.co.tireerp.domain.entity.Address;
import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "delivery")
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Sale sale;

    @Column(name = "recipient_name")
    private String recipientName;

    @Embedded
    private Address address;

    @Column(name = "recipient_phone_number")
    private String recipientPhoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_option")
    private DeliveryOption deliveryOption;

    @Column(name = "delivery_company")
    private String deliveryCompany;

    @Column(name = "invoice_number")
    private String invoiceNumber;
}
