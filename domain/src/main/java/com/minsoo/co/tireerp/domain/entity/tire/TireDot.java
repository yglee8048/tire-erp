package com.minsoo.co.tireerp.domain.entity.tire;

import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import com.minsoo.co.tireerp.domain.entity.stock.Stock;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "tire_dot")
public class TireDot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tire_dot_id", length = 20)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tire_id")
    private Tire tire;

    @Column(name = "dot")
    private String dot;

    @OneToMany(mappedBy = "tireDot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<Stock> stocks = new HashSet<>();

    public TireDot setTire(Tire tire) {
        this.tire = tire;
        return this;
    }

    public TireDot update(TireDot update, Tire tire) {
        this.tire = tire;
        this.dot = update.dot;
        return this;
    }

    public Integer getSumOfStock(Boolean lock) {
        return stocks.stream()
                .filter(stock -> lock == null || stock.getLock().equals(lock))
                .mapToInt(Stock::getQuantity)
                .sum();
    }
}
