package com.minsoo.co.tireerp.domain.entity;

import com.minsoo.co.tireerp.domain.constant.AccountRole;
import com.minsoo.co.tireerp.domain.constant.DeliveryOption;
import com.minsoo.co.tireerp.domain.constant.PatternSeason;
import com.minsoo.co.tireerp.domain.constant.SaleSource;
import com.minsoo.co.tireerp.domain.entity.admin.Admin;
import com.minsoo.co.tireerp.domain.entity.client.Client;
import com.minsoo.co.tireerp.domain.entity.client.ClientCompany;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.management.Vendor;
import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import com.minsoo.co.tireerp.domain.entity.purchase.Purchase;
import com.minsoo.co.tireerp.domain.entity.purchase.PurchaseContent;
import com.minsoo.co.tireerp.domain.entity.rank.Rank;
import com.minsoo.co.tireerp.domain.entity.rank.RankDotPrice;
import com.minsoo.co.tireerp.domain.entity.sale.Delivery;
import com.minsoo.co.tireerp.domain.entity.sale.Sale;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.minsoo.co.tireerp.domain.entity.tire.TireMemo;

import java.time.LocalDate;

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

    public static Purchase purchase() {
        return Purchase.builder()
                .vendor(null)
                .transactionDate(LocalDate.now())
                .description("매입 테스트 용도")
                .build();
    }

    public static PurchaseContent purchaseContent() {
        return PurchaseContent.builder()
                .purchase(null)
                .stock(null)
                .price(1000L)
                .quantity(10)
                .build();
    }

    public static PurchaseContent purchaseContent2() {
        return PurchaseContent.builder()
                .purchase(null)
                .stock(null)
                .price(2000L)
                .quantity(10)
                .build();
    }

    public static Admin admin() {
        return Admin.builder()
                .role(AccountRole.ROOT)
                .username("test")
                .password("test")
                .name("test_user")
                .email("test@test.com")
                .phoneNumber("010-1234-5678")
                .description("테스트 용도 관리자")
                .build();
    }

    public static Admin admin2() {
        return Admin.builder()
                .role(AccountRole.ADMIN)
                .username("test2")
                .password("test")
                .name("test_user")
                .email("test@test.com")
                .phoneNumber("010-1234-5678")
                .description("테스트 용도 관리자")
                .build();
    }

    public static Rank rank() {
        return Rank.builder()
                .name("테스트 등급")
                .description("테스트 용도 등급")
                .build();
    }

    public static Rank rank2() {
        return Rank.builder()
                .name("테스트 등급2")
                .description("테스트 용도 등급")
                .build();
    }

    public static RankDotPrice rankDotPrice() {
        return RankDotPrice.builder()
                .rank(null)
                .tireDot(null)
                .discountedPrice(2000L)
                .build();
    }

    public static RankDotPrice rankDotPrice2() {
        return RankDotPrice.builder()
                .rank(null)
                .tireDot(null)
                .discountedPrice(4000L)
                .build();
    }

    public static ClientCompany clientCompany() {
        return ClientCompany.builder()
                .rank(null)
                .name("테스트 고객사")
                .description("테스트 용도 고객사")
                .businessInfo(businessInfo())
                .build();
    }

    public static ClientCompany clientCompany2() {
        return ClientCompany.builder()
                .rank(null)
                .name("테스트 고객사2")
                .description("테스트 용도 고객사")
                .businessInfo(businessInfo())
                .build();
    }

    public static Client client() {
        return Client.builder()
                .clientCompany(null)
                .role(AccountRole.CLIENT)
                .username("test")
                .password("test")
                .name("test_user")
                .email("test@test.com")
                .phoneNumber("010-1234-5678")
                .description("테스트 용도 고객")
                .address(address())
                .build();
    }

    public static Client client2() {
        return Client.builder()
                .clientCompany(null)
                .role(AccountRole.CLIENT)
                .username("test2")
                .password("test")
                .name("test_user")
                .email("test@test.com")
                .phoneNumber("010-1234-5678")
                .description("테스트 용도 고객")
                .address(address())
                .build();
    }

    public static Sale sale() {
        return Sale.builder()
                .clientCompany(null)
                .source(SaleSource.MANUAL)
                .transactionDate(LocalDate.now())
                .releaseDate(LocalDate.now())
                .desiredDeliveryDate(LocalDate.now())
                .build();
    }

    public static Sale sale2() {
        return Sale.builder()
                .clientCompany(null)
                .source(SaleSource.ONLINE)
                .transactionDate(LocalDate.now())
                .releaseDate(LocalDate.now())
                .desiredDeliveryDate(LocalDate.now())
                .build();
    }

    public static Delivery delivery(){
        return Delivery.builder()
                .sale(null)
                .recipientName("tester")
                .address(address())
                .recipientPhoneNumber("010-1234-5678")
                .deliveryOption(DeliveryOption.PACKAGE)
                .deliveryCompany("test company")
                .invoiceNumber("1234-5678")
                .build();
    }

    public static Delivery delivery2(){
        return Delivery.builder()
                .sale(null)
                .recipientName("tester2")
                .address(address())
                .recipientPhoneNumber("010-1234-5678")
                .deliveryOption(DeliveryOption.PACKAGE)
                .deliveryCompany("test company")
                .invoiceNumber("1234-5678")
                .build();
    }
}
