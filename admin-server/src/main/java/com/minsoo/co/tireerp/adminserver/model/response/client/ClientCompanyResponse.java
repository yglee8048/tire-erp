package com.minsoo.co.tireerp.adminserver.model.response.client;

import com.minsoo.co.tireerp.adminserver.model.BusinessInfoDTO;
import com.minsoo.co.tireerp.core.entity.client.ClientCompany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ClientCompanyResponse {

    private Long clientCompanyId;
    private String name;
    private String description;
    private BusinessInfoDTO businessInfo;

    private Long rankId;

    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;

    public ClientCompanyResponse(ClientCompany clientCompany) {
        this.clientCompanyId = clientCompany.getId();
        this.name = clientCompany.getName();
        this.description = clientCompany.getDescription();
        this.businessInfo = new BusinessInfoDTO(clientCompany.getBusinessInfo());

        this.rankId = clientCompany.getRank().getId();

        this.createdAt = clientCompany.getCreatedAt();
        this.lastModifiedAt = clientCompany.getLastModifiedAt();
        this.createdBy = clientCompany.getCreatedBy();
        this.lastModifiedBy = clientCompany.getLastModifiedBy();
    }
}
