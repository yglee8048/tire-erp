package com.minsoo.co.tireerp.domain.entity.stock;

import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "stock")
public class Stock extends BaseEntity {

    @Id
    @Column(name = "stock_id", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tire_dot_id", nullable = false)
    private TireDot tireDot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "is_lock", nullable = false)
    private Boolean lock;

    public Stock setTireDot(TireDot tireDot) {
        this.tireDot = tireDot;
        return this;
    }

    public Stock setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        return this;
    }

    public Stock addQuantity(Integer quantity) {
        this.quantity += quantity;
        return this;
    }

    public Stock setLock(Boolean lock) {
        this.lock = lock;
        return this;
    }
}
