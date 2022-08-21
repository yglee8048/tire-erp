package com.minsoo.co.tireerp.adminserver.model.response.tire;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerTireDotGridResponse {

    private TireDotResponse tireDot;

    private Long price;

    private Integer sumOfOpenedStock;

    public CustomerTireDotGridResponse(TireDotGridResponse tireDotGridResponse) {
        this.tireDot = tireDotGridResponse.getTireDot();


        this.sumOfOpenedStock = tireDotGridResponse.getSumOfOpenedStock();
    }
}
