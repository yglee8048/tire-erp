package com.minsoo.co.tireerp.adminserver.model.response.tire;

import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TireDotResponse {

    private Long tireDotId;
    private Long tireId;
    private String dot;

    public TireDotResponse(TireDot tireDot) {
        this.tireDotId = tireDot.getId();
        this.tireId = tireDot.getTire().getId();
        this.dot = tireDot.getDot();
    }
}
