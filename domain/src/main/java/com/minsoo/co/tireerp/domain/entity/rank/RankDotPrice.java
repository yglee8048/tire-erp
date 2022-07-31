package com.minsoo.co.tireerp.domain.entity.rank;

import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import com.minsoo.co.tireerp.domain.entity.tire.TireDot;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "rank_dot_price")
public class RankDotPrice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_dot_price_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id", referencedColumnName = "rank_id")
    private Rank rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tire_dot_id", referencedColumnName = "tire_dot_id")
    private TireDot tireDot;

    @Column(name = "discounted_rice")
    private Long discountedPrice;

    public RankDotPrice setRank(Rank rank) {
        this.rank = rank;
        return this;
    }

    public RankDotPrice setTireDot(TireDot tireDot) {
        this.tireDot = tireDot;
        return this;
    }

    public RankDotPrice update(RankDotPrice update) {
        this.discountedPrice = update.discountedPrice;
        return this;
    }
}
