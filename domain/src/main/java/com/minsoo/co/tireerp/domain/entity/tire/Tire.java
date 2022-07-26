package com.minsoo.co.tireerp.domain.entity.tire;

import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "tire")
public class Tire extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tire_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pattern_id")
    private Pattern pattern;

    @Column(name = "tire_code")
    private String tireCode;

    @Column(name = "width")
    private Integer width;

    @Column(name = "flatness_ratio")
    private String flatnessRatio;

    @Column(name = "inch")
    private Integer inch;

    @Column(name = "size")
    private String size;

    @Column(name = "oe")
    private String oe;

    @Column(name = "load_index")
    private Integer loadIndex;

    @Column(name = "speed_index")
    private String speedIndex;

    @Column(name = "run_flat")
    private Boolean runFlat;

    @Column(name = "sponge")
    private Boolean sponge;

    @Column(name = "sealing")
    private Boolean sealing;

    @Column(name = "factory_price")
    private Long factoryPrice;

    @Column(name = "country_of_manufacture")
    private String countryOfManufacture;

    @OneToMany(mappedBy = "tire", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<TireDot> tireDots = new HashSet<>();

    public Tire setPattern(Pattern pattern) {
        this.pattern = pattern;
        return this;
    }

    public Tire update(Tire update, Pattern pattern) {
        this.pattern = pattern;
        this.tireCode = update.tireCode;
        this.width = update.width;
        this.flatnessRatio = update.flatnessRatio;
        this.inch = update.inch;
        this.size = update.size;
        this.oe = update.oe;
        this.loadIndex = update.loadIndex;
        this.speedIndex = update.speedIndex;
        this.runFlat = update.runFlat;
        this.sponge = update.sponge;
        this.sealing = update.sealing;
        this.factoryPrice = update.factoryPrice;
        this.countryOfManufacture = update.countryOfManufacture;
        return this;
    }
}
