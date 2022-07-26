package com.minsoo.co.tireerp.domain.entity;

import com.minsoo.co.tireerp.domain.constant.PatternSeason;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.management.Vendor;
import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.minsoo.co.tireerp.domain.entity.tire.TireMemo;

public class EntitySnippet {

    public static Address address() {
        return Address.builder()
                .city("서울시")
                .streetAddress("마곡중앙 8로 71")
                .detailAddress("E13")
                .zipCode("123456")
                .build();
    }

    public static BusinessInfo businessInfo() {
        return BusinessInfo.builder()
                .businessNumber("1234567890")
                .businessName("사업자 이름")
                .businessType("사업 종류")
                .address(address())
                .fax("010-1234-5678")
                .email("test@test.com")
                .representative("대표")
                .representativePhoneNumber("010-1234-5678")
                .manager("담당자")
                .managerPhoneNumber("010-1234-5678")
                .build();
    }

    public static Brand brand() {
        return Brand.builder()
                .name("테스트 제조사")
                .description("제조사 테스트 용도")
                .build();
    }

    public static Brand brand2() {
        return Brand.builder()
                .name("테스트 제조사2")
                .description("제조사 테스트 용도")
                .build();
    }

    public static Pattern pattern() {
        return Pattern.builder()
                .brand(null)
                .name("테스트 패턴")
                .englishName("test-pattern")
                .description("패턴 테스트 용도")
                .season(PatternSeason.ALL_SEASON)
                .quietness(true)
                .rideQuality(true)
                .mileage(false)
                .handling(false)
                .breakingPower(false)
                .wetSurface(true)
                .snowPerformance(true)
                .build();
    }

    public static Pattern pattern2() {
        return Pattern.builder()
                .brand(null)
                .name("테스트 패턴2")
                .englishName("test-pattern")
                .description("패턴 테스트 용도")
                .season(PatternSeason.FOR_SUMMER)
                .quietness(true)
                .rideQuality(true)
                .mileage(false)
                .handling(false)
                .breakingPower(false)
                .wetSurface(true)
                .snowPerformance(true)
                .build();
    }

    public static Warehouse warehouse() {
        return Warehouse.builder()
                .name("테스트 창고")
                .description("창고 테스트 용도")
                .address(address())
                .build();
    }

    public static Warehouse warehouse2() {
        return Warehouse.builder()
                .name("테스트 창고2")
                .description("창고 테스트 용도")
                .address(address())
                .build();
    }

    public static Vendor vendor() {
        return Vendor.builder()
                .name("테스트 매입처")
                .description("매입처 테스트 용도")
                .businessInfo(businessInfo())
                .build();
    }

    public static Vendor vendor2() {
        return Vendor.builder()
                .name("테스트 매입처2")
                .description("매입처 테스트 용도")
                .businessInfo(businessInfo())
                .build();
    }

    public static Tire tire() {
        return Tire.builder()
                .pattern(null)
                .tireCode("1")
                .width(123)
                .flatnessRatio("45")
                .inch(23)
                .size("123/45R23")
                .oe("LR")
                .loadIndex(123)
                .speedIndex("W")
                .runFlat(true)
                .sponge(false)
                .sealing(true)
                .factoryPrice(100000L)
                .countryOfManufacture("미국")
                .build();
    }

    public static Tire tire2() {
        return Tire.builder()
                .pattern(null)
                .tireCode("2")
                .width(123)
                .flatnessRatio("45")
                .inch(23)
                .size("123/45R23")
                .oe("LR")
                .loadIndex(123)
                .speedIndex("W")
                .runFlat(true)
                .sponge(false)
                .sealing(true)
                .factoryPrice(100000L)
                .countryOfManufacture("미국")
                .build();
    }

    public static TireDot tireDot() {
        return TireDot.builder()
                .tire(null)
                .dot("1111")
                .build();
    }

    public static TireDot tireDot2() {
        return TireDot.builder()
                .tire(null)
                .dot("2222")
                .build();
    }

    public static TireMemo tireMemo() {
        return TireMemo.builder()
                .tire(null)
                .memo("memo")
                .lock(false)
                .build();
    }

    public static TireMemo tireMemo2() {
        return TireMemo.builder()
                .tire(null)
                .memo("memo2")
                .lock(true)
                .build();
    }

    public static Stock stock() {
        return Stock.builder()
                .nickname("테스트 재고")
                .quantity(10)
                .lock(false)
                .build();
    }

    public static Stock stock2() {
        return Stock.builder()
                .nickname("테스트 재고2")
                .quantity(20)
                .lock(true)
                .build();
    }
}
