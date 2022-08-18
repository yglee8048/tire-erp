package com.minsoo.co.tireerp.adminserver.repository.tire;

import com.minsoo.co.tireerp.adminserver.model.response.tire.TireDotGridResponse;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.minsoo.co.tireerp.domain.entity.purchase.QPurchaseContent.purchaseContent;
import static com.minsoo.co.tireerp.domain.entity.stock.QStock.stock;
import static com.minsoo.co.tireerp.domain.entity.tire.QTireDot.tireDot;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TireDotGridRepository {

    private final JPAQueryFactory queryFactory;

    public List<TireDotGridResponse> getTireDotGridResponsesByTireId(Long tireId) {
        // select tire-dots by tire id
        List<TireDot> tireDots = selectTireDotsByTireId(tireId);

        // collect tire-dot ids
        List<Long> tireDotIds = tireDots.stream()
                .map(TireDot::getId)
                .collect(Collectors.toList());

        // calculate stocks
        Map<Long, TireDotStock> tireDotStocksByTireDotId = calculateTireDotStock(tireDotIds);

        // calculate avg of purchase price
        Map<Long, Double> purchasePriceAveragesByTireDotId = calculatePurchasePriceAverage(tireDotIds);

        // compose
        return tireDots
                .stream()
                .map(tireDot -> {
                    return TireDotGridResponse.builder()
                            .tireDot(tireDot)
                            .sumOfOpenedStock(tireDotStocksByTireDotId.get(tireDot.getId()).getSumOfOpenedStock())
                            .sumOfStock(tireDotStocksByTireDotId.get(tireDot.getId()).getSumOfStock())
                            .averageOfPurchasePrice(purchasePriceAveragesByTireDotId.get(tireDot.getId()))
                            .build();
                })
                .collect(Collectors.toList());
    }

    private List<TireDot> selectTireDotsByTireId(Long tireId) {
        return queryFactory
                .select(tireDot)
                .from(tireDot)
                .where(tireDot.tire.id.eq(tireId))
                .fetch();
    }

    private Map<Long, TireDotStock> calculateTireDotStock(List<Long> tireDotIds) {
        return queryFactory
                .select(Projections.fields(TireDotStock.class,
                        tireDot.id.as("tireDotId"),
                        new CaseBuilder()
                                .when(stock.lock.eq(false))
                                .then(stock.quantity)
                                .otherwise(0).sum().as("sumOfOpenedStock"),
                        stock.quantity.sum().as("sumOfStock")))
                .from(stock)
                .where(stock.tireDot.id.in(tireDotIds))
                .groupBy(stock.tireDot)
                .fetch()
                .stream()
                .collect(Collectors.toMap(TireDotStock::getTireDotId, o -> o));
    }

    private Map<Long, Double> calculatePurchasePriceAverage(List<Long> tireDotIds) {
        return queryFactory
                .select(Projections.fields(TireDotPurchasePriceAvg.class,
                        stock.tireDot.id.as("tireDotId"),
                        purchaseContent.price.avg().as("averageOfPurchasePrice")))
                .from(purchaseContent)
                .join(purchaseContent.stock, stock)
                .where(tireDot.id.in(tireDotIds))
                .groupBy(stock.tireDot)
                .fetch()
                .stream()
                .collect(Collectors.toMap(TireDotPurchasePriceAvg::getTireDotId, TireDotPurchasePriceAvg::getAverageOfPurchasePrice));
    }

    @Getter
    @NoArgsConstructor
    private static class TireDotStock {
        private Long tireDotId;
        private Integer sumOfOpenedStock;
        private Integer sumOfStock;
    }

    @Getter
    @NoArgsConstructor
    private static class TireDotPurchasePriceAvg {
        private Long tireDotId;
        private Double averageOfPurchasePrice;
    }
}
