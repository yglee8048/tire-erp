package com.minsoo.co.tireerp.domain.entity.sale;

import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "sale_content")
public class SaleContent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_content_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", referencedColumnName = "sale_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
    private Stock stock;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Integer quantity;

    public SaleContent setSale(Sale sale) {
        this.sale = sale;
        return this;
    }

    public SaleContent setStock(Stock stock) {
        this.stock = stock;
        return this;
    }

    public SaleContent update(SaleContent update, Stock stock) {
        this.stock = stock;
        this.price = update.price;
        this.quantity = update.quantity;
        return this;
    }
}
