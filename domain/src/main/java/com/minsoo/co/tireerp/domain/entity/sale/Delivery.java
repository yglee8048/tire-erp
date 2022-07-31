package com.minsoo.co.tireerp.domain.entity.sale;

import com.minsoo.co.tireerp.domain.constant.DeliveryOption;
import com.minsoo.co.tireerp.domain.entity.Address;
import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Sale sale;

    public Delivery update(Delivery update) {
        this.recipientName = update.recipientName;
        this.address = update.address;
        this.recipientPhoneNumber = update.recipientPhoneNumber;
        this.deliveryOption = update.deliveryOption;
        this.deliveryCompany = update.deliveryCompany;
        this.invoiceNumber = update.invoiceNumber;
        return this;
    }
}
