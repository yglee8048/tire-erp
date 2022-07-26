package com.minsoo.co.tireerp.domain.entity.management;

import com.minsoo.co.tireerp.domain.constant.PatternSeason;
import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "pattern")
public class Pattern extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pattern_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "name")
    private String name;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "season")
    private PatternSeason season;

    @Column(name = "quietness")
    private Boolean quietness;

    @Column(name = "ride_quality")
    private Boolean rideQuality;

    @Column(name = "mileage")
    private Boolean mileage;

    @Column(name = "handling")
    private Boolean handling;

    @Column(name = "breaking_power")
    private Boolean breakingPower;

    @Column(name = "wet_surface")
    private Boolean wetSurface;

    @Column(name = "snow_performance")
    private Boolean snowPerformance;

    public Pattern setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public Pattern update(Pattern update, Brand brand) {
        this.brand = brand;
        this.name = update.name;
        this.englishName = update.englishName;
        this.description = update.description;
        this.season = update.season;
        this.quietness = update.quietness;
        this.rideQuality = update.rideQuality;
        this.mileage = update.mileage;
        this.handling = update.handling;
        this.breakingPower = update.breakingPower;
        this.wetSurface = update.wetSurface;
        this.snowPerformance = update.snowPerformance;
        return this;
    }
}
