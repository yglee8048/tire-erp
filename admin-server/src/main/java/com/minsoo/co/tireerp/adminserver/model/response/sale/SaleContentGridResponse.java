package com.minsoo.co.tireerp.adminserver.model.response.sale;

import com.minsoo.co.tireerp.adminserver.model.TireInfoResponse;
import com.minsoo.co.tireerp.adminserver.model.response.client.ClientCompanyResponse;
import com.minsoo.co.tireerp.adminserver.model.response.stock.StockGridResponse;
import com.minsoo.co.tireerp.adminserver.model.response.tire.TireDotGridResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SaleContentGridResponse {

    private Long saleContentId;
    private SaleResponse sale;

    private ClientCompanyResponse clientCompany;

    private TireInfoResponse tireInfo;

    private Long tireDotId;
    private TireDotGridResponse tireDotInfo;

    private Integer quantity;
    private Long price;
    private Long salePrice;

    private StockGridResponse stockInfo;

    private DeliveryResponse delivery;

    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;

    public SaleContentGridResponse setTireDotInfo(TireDotGridResponse tireDotGridResponse) {
        this.tireDotInfo = tireDotGridResponse;
        return this;
    }
}
