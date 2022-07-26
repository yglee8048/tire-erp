package com.minsoo.co.tireerp.adminserver.model.response.rank;

import com.minsoo.co.tireerp.adminserver.model.response.tire.TireDotResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankDotPriceGridResponse {

    private RankResponse rank;
    private TireDotResponse tireDot;
    private Float discountRate;
}
