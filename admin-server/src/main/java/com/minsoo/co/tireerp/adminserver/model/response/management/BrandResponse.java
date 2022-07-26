package com.minsoo.co.tireerp.adminserver.model.response.management;

import com.minsoo.co.tireerp.domain.entity.management.Brand;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BrandResponse {

    private Long brandId;
    private String name;
    private String description;

    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;

    private BrandResponse(Brand brand) {
        this.brandId = brand.getId();
        this.name = brand.getName();
        this.description = brand.getDescription();

        this.createdAt = brand.getCreatedAt();
        this.lastModifiedAt = brand.getLastModifiedAt();
        this.createdBy = brand.getCreatedBy();
        this.lastModifiedBy = brand.getLastModifiedBy();
    }

    public static BrandResponse from(Brand brand) {
        return new BrandResponse(brand);
    }
}
