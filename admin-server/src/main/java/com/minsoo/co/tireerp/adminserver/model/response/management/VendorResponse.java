package com.minsoo.co.tireerp.adminserver.model.response.management;

import com.minsoo.co.tireerp.adminserver.model.BusinessInfoDTO;
import com.minsoo.co.tireerp.domain.entity.management.Vendor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class VendorResponse {

    private Long vendorId;
    private String name;
    private String description;
    private BusinessInfoDTO businessInfo;

    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;

    private VendorResponse(Vendor vendor) {
        this.vendorId = vendor.getId();
        this.name = vendor.getName();
        this.description = vendor.getDescription();
        this.businessInfo = new BusinessInfoDTO(vendor.getBusinessInfo());

        this.createdAt = vendor.getCreatedAt();
        this.lastModifiedAt = vendor.getLastModifiedAt();
        this.createdBy = vendor.getCreatedBy();
        this.lastModifiedBy = vendor.getLastModifiedBy();
    }

    public static VendorResponse from(Vendor vendor) {
        return new VendorResponse(vendor);
    }
}
