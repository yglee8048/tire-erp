package com.minsoo.co.tireerp.adminserver.model.response.sale;

import com.minsoo.co.tireerp.adminserver.model.AddressDTO;
import com.minsoo.co.tireerp.domain.constant.DeliveryOption;
import com.minsoo.co.tireerp.domain.entity.sale.Delivery;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DeliveryResponse {

    private Long deliveryId;
    private String recipientName;
    private AddressDTO address;
    private String recipientPhoneNumber;
    private DeliveryOption deliveryOption;
    private String deliveryCompany;
    private String invoiceNumber;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private String createdBy;
    private String lastModifiedBy;

    public DeliveryResponse(Delivery delivery) {
        this.deliveryId = delivery.getId();
        this.recipientName = delivery.getRecipientName();
        this.address = new AddressDTO(delivery.getAddress());
        this.recipientPhoneNumber = delivery.getRecipientPhoneNumber();
        this.deliveryOption = delivery.getDeliveryOption();
        this.deliveryCompany = delivery.getDeliveryCompany();
        this.invoiceNumber = delivery.getInvoiceNumber();

        this.createdAt = delivery.getCreatedAt();
        this.lastModifiedAt = delivery.getLastModifiedAt();
        this.createdBy = delivery.getCreatedBy();
        this.lastModifiedBy = delivery.getLastModifiedBy();
    }
}
