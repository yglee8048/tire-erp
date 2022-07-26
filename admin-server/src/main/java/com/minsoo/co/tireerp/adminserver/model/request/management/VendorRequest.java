package com.minsoo.co.tireerp.adminserver.model.request.management;

import com.minsoo.co.tireerp.adminserver.model.BusinessInfoDTO;
import com.minsoo.co.tireerp.domain.entity.management.Vendor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorRequest {

    @Schema(name = "name", description = "이름", example = "피렐리 코리아", required = true)
    @NotEmpty(message = "매입처 이름은 필수 값입니다.")
    private String name;

    @Schema(name = "description", description = "설명", example = "20년 5월부터 계약 시작")
    private String description;

    @Schema(name = "business_info", description = "사업자 관련 정보")
    private BusinessInfoDTO businessInfo;

    public Vendor toEntity() {
        return Vendor.builder()
                .name(this.name)
                .description(this.description)
                .businessInfo(businessInfo == null ? null : this.businessInfo.toEntity())
                .build();
    }
}
