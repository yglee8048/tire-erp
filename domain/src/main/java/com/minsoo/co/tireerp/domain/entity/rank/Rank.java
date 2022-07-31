package com.minsoo.co.tireerp.domain.entity.rank;

import com.minsoo.co.tireerp.domain.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "rank")
public class Rank extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Rank update(Rank update) {
        this.name = update.name;
        this.description = update.description;
        return this;
    }
}
