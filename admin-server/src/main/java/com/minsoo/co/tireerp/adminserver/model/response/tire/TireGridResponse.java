package com.minsoo.co.tireerp.adminserver.model.response.tire;

import com.minsoo.co.tireerp.adminserver.model.TireInfoResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TireGridResponse {

    private TireInfoResponse tireInfo;

    private Integer sumOfOpenedStock;
    private Integer sumOfStock;
    private Integer theNumberOfActiveDots;

    private Double averageOfPurchasePrice;

    @Builder
    public TireGridResponse(TireInfoResponse tireInfo, Integer sumOfOpenedStock, Integer sumOfStock, Integer theNumberOfActiveDots, Double averageOfPurchasePrice) {
        this.tireInfo = tireInfo;
        this.sumOfOpenedStock = sumOfOpenedStock;
        this.sumOfStock = sumOfStock;
        this.theNumberOfActiveDots = theNumberOfActiveDots;
        this.averageOfPurchasePrice = averageOfPurchasePrice;
    }
}
