package com.minsoo.co.tireerp.adminserver.repository.tire;

import com.minsoo.co.tireerp.adminserver.model.TireInfoResponse;
import com.minsoo.co.tireerp.adminserver.model.response.tire.TireGridResponse;
import com.minsoo.co.tireerp.domain.entity.management.Brand;
import com.minsoo.co.tireerp.domain.entity.management.Pattern;
import com.minsoo.co.tireerp.domain.entity.tire.Tire;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.minsoo.co.tireerp.domain.entity.management.QBrand.brand;
import static com.minsoo.co.tireerp.domain.entity.management.QPattern.pattern;
import static com.minsoo.co.tireerp.domain.entity.purchase.QPurchaseContent.purchaseContent;
import static com.minsoo.co.tireerp.domain.entity.stock.QStock.stock;
import static com.minsoo.co.tireerp.domain.entity.tire.QTire.tire;
import static com.minsoo.co.tireerp.domain.entity.tire.QTireDot.tireDot;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TireGridRepository {

    private final JPAQueryFactory queryFactory;

    public List<TireGridResponse> getTireGridResponses() {
        // select all tire-dots
        List<TireDotQueryGroup> tireDotQueryGroups = selectTireDotQueryGroups();

        // collect tire-dot ids
        List<Long> tireDotIds = tireDotQueryGroups.stream()
                .map(TireDotQueryGroup::getTireDot)
                .map(TireDot::getId)
                .collect(Collectors.toList());

        // calculate stock quantity by tire-dot
        Map<Long, TireStock> tireStocksByTireId = calculateTireStock(tireDotIds);

        // calculate avg of purchase price by tire-dot
        Map<Long, Double> purchasePriceAveragesByTireId = calculatePurchasePriceAverage(tireDotIds);

        // compose
        return tireDotQueryGroups
                .stream()
                .collect(Collectors.groupingBy(TireDotQueryGroup::getTire))
                .entrySet().stream()
                .map(tireListEntry -> {
                    Tire tire = tireListEntry.getKey();
                    return TireGridResponse.builder()
                            .tireInfo(TireInfoResponse.builder()
                                    .tire(tire)
                                    .pattern(tireListEntry.getValue().get(0).getPattern())
                                    .brand(tireListEntry.getValue().get(0).getBrand())
                                    .build())
                            .sumOfOpenedStock(tireStocksByTireId.get(tire.getId()).getSumOfOpenedStock())
                            .sumOfStock(tireStocksByTireId.get(tire.getId()).getSumOfStock())
                            .theNumberOfActiveDots(tireStocksByTireId.get(tire.getId()).getTheNumberOfActiveTireDots())
                            .averageOfPurchasePrice(purchasePriceAveragesByTireId.get(tire.getId()))
                            .build();
                })
                .collect(Collectors.toList());
    }

    private List<TireDotQueryGroup> selectTireDotQueryGroups() {
        return queryFactory
                .select(Projections.fields(TireDotQueryGroup.class,
                        tireDot,
                        tire,
                        pattern,
                        brand))
                .from(tireDot)
                .join(tireDot.tire, tire)
                .join(tire.pattern, pattern)
                .join(pattern.brand, brand)
                .fetch();
    }

    private Map<Long, Double> calculatePurchasePriceAverage(List<Long> tireDotIds) {
        return queryFactory
                .select(Projections.fields(TirePurchasePriceAvg.class,
                        tireDot.tire.id.as("tireId"),
                        purchaseContent.price.avg().as("averageOfPurchasePrice")))
                .from(purchaseContent)
                .join(purchaseContent.stock, stock)
                .join(stock.tireDot, tireDot)
                .where(tireDot.id.in(tireDotIds))
                .groupBy(tireDot.tire)
                .fetch()
                .stream()
                .collect(Collectors.toMap(TirePurchasePriceAvg::getTireId, TirePurchasePriceAvg::getAverageOfPurchasePrice));
    }

    private Map<Long, TireStock> calculateTireStock(List<Long> tireDotIds) {
        return queryFactory
                .select(Projections.fields(TireStock.class,
                        tire.id.as("tireId"),
                        new CaseBuilder()
                                .when(stock.lock.eq(true))
                                .then(stock.quantity)
                                .otherwise(0).sum().as("sumOfOpenedStock"),
                        stock.quantity.sum().as("sumOfStock"),
                        new CaseBuilder()
                                .when(stock.quantity.gt(0))
                                .then(tireDot)
                                .otherwise(Expressions.nullExpression())
                                .countDistinct().as("theNumberOfActiveTireDots")))
                .from(stock)
                .join(stock.tireDot, tireDot)
                .join(tireDot.tire, tire)
                .where(tireDot.id.in(tireDotIds))
                .groupBy(tire)
                .fetch()
                .stream()
                .collect(Collectors.toMap(TireStock::getTireId, o -> o));
    }

    @Getter
    @NoArgsConstructor
    private static class TireDotQueryGroup {
        private TireDot tireDot;
        private Tire tire;
        private Pattern pattern;
        private Brand brand;
    }

    @Getter
    @NoArgsConstructor
    private static class TirePurchasePriceAvg {
        private Long tireId;
        private Double averageOfPurchasePrice;
    }

    @Getter
    @NoArgsConstructor
    private static class TireStock {
        private Long tireId;
        private Integer sumOfOpenedStock;
        private Integer sumOfStock;
        private Integer theNumberOfActiveTireDots;
    }
}
