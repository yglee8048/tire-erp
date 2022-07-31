package com.minsoo.co.tireerp.domain.entity.sale;

import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "sale_memo")
public class SaleMemo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_memo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", referencedColumnName = "sale_id")
    private Sale sale;

    @Column(name = "memo")
    private String memo;

    @Column(name = "is_admin")
    private Boolean admin;

    @Column(name = "is_lock")
    private Boolean lock;

    public SaleMemo setSale(Sale sale) {
        this.sale = sale;
        return this;
    }

    public SaleMemo update(SaleMemo update) {
        this.memo = update.memo;
        this.admin = update.admin;
        this.lock = update.lock;
        return this;
    }
}
