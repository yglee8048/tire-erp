package com.minsoo.co.tireerp.adminserver.model.response.tire;

import com.minsoo.co.tireerp.adminserver.model.TireInfoResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerTireGridResponse {

    private TireInfoResponse tireInfo;

    private Integer sumOfOpenedStock;
    private Integer theNumberOfActiveDots;

    public CustomerTireGridResponse(TireGridResponse tireGridResponse) {
        this.tireInfo = tireGridResponse.getTireInfo();

        this.sumOfOpenedStock = tireGridResponse.getSumOfOpenedStock();
        this.theNumberOfActiveDots = tireGridResponse.getTheNumberOfActiveDots();
    }
}
