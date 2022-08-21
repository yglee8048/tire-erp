package com.minsoo.co.tireerp.adminserver.model;

import com.minsoo.co.tireerp.adminserver.model.request.management.BrandRequest;
import com.minsoo.co.tireerp.adminserver.model.request.management.PatternRequest;
import com.minsoo.co.tireerp.adminserver.model.request.tire.TireRequest;

public class ModelSnippet {

    public static BrandRequest brandRequest(String name){
        return BrandRequest.builder()
                .name(name)
                .description("test")
                .build();
    }

    public static PatternRequest patternRequest(String name){
        return PatternRequest.builder()
                .name(name)
                .description("test")
                .build();
    }

    public static TireRequest tireRequest(Long patternId, String tireCode){
        return TireRequest.builder()
                .patternId(patternId)
                .tireCode(tireCode)
                .width(123)
                .flatnessRatio("12")
                .inch(2)
                .size("123/12R2")
                .oe("oe")
                .loadIndex(123)
                .speedIndex("123")
                .runFlat(false)
                .sponge(true)
                .sealing(false)
                .factoryPrice(12345L)
                .countryOfManufacture("ko")
                .build();
    }
}
