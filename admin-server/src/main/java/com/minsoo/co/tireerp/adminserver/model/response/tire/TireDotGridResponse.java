package com.minsoo.co.tireerp.adminserver.model.response.tire;

import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TireDotGridResponse {

    private TireDotResponse tireDot;

    private Integer sumOfOpenedStock;
    private Integer sumOfStock;

    private Double averageOfPurchasePrice;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private String createdBy;
    private String lastModifiedBy;

    @Builder
    public TireDotGridResponse(TireDot tireDot, Integer sumOfOpenedStock, Integer sumOfStock, Double averageOfPurchasePrice) {
        this.tireDot = new TireDotResponse(tireDot);
        this.sumOfOpenedStock = sumOfOpenedStock;
        this.sumOfStock = sumOfStock;
        this.averageOfPurchasePrice = averageOfPurchasePrice;
        this.createdAt = tireDot.getCreatedAt();
        this.lastModifiedAt = tireDot.getLastModifiedAt();
        this.createdBy = tireDot.getCreatedBy();
        this.lastModifiedBy = tireDot.getLastModifiedBy();
    }
}
