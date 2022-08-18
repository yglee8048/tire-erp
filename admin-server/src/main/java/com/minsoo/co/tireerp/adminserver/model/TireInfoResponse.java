package com.minsoo.co.tireerp.adminserver.model;

import com.minsoo.co.tireerp.domain.constant.PatternSeason;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TireInfoResponse {

    private Long tireId;

    private Long brandId;
    private String brandName;

    private Long patternId;
    private String patternName;
    private String patternEnglishName;

    private Integer width;
    private String flatnessRatio;
    private Integer inch;
    private String size;
    private String oe;
    private Integer loadIndex;
    private String speedIndex;

    private Boolean runFlat;
    private Boolean sponge;
    private Boolean sealing;

    private Long factoryPrice;
    private String countryOfManufacture;

    private PatternSeason season;
    private Boolean quietness;
    private Boolean rideQuality;
    private Boolean mileage;
    private Boolean handling;
    private Boolean breakingPower;
    private Boolean wetSurface;
    private Boolean snowPerformance;

    private String tireCode;

    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;

    @Builder
    public TireInfoResponse(Tire tire, Pattern pattern, Brand brand) {
        this.tireId = tire.getId();
        this.brandId = brand.getId();
        this.brandName = brand.getName();
        this.patternId = pattern.getId();
        this.patternName = pattern.getName();
        this.patternEnglishName = pattern.getEnglishName();
        this.width = tire.getWidth();
        this.flatnessRatio = tire.getFlatnessRatio();
        this.inch = tire.getInch();
        this.size = tire.getSize();
        this.oe = tire.getOe();
        this.loadIndex = tire.getLoadIndex();
        this.speedIndex = tire.getSpeedIndex();
        this.runFlat = tire.getRunFlat();
        this.sponge = tire.getSponge();
        this.sealing = tire.getSealing();
        this.factoryPrice = tire.getFactoryPrice();
        this.countryOfManufacture = tire.getCountryOfManufacture();
        this.season = pattern.getSeason();
        this.quietness = pattern.getQuietness();
        this.rideQuality = pattern.getRideQuality();
        this.mileage = pattern.getMileage();
        this.handling = pattern.getHandling();
        this.breakingPower = pattern.getBreakingPower();
        this.wetSurface = pattern.getWetSurface();
        this.snowPerformance = pattern.getSnowPerformance();
        this.tireCode = tire.getTireCode();
        this.createdBy = tire.getCreatedBy();
        this.createdAt = tire.getCreatedAt();
        this.lastModifiedBy = tire.getLastModifiedBy();
        this.lastModifiedAt = tire.getLastModifiedAt();
    }
}
