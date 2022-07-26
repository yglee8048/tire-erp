package com.minsoo.co.tireerp.adminserver.model.response.management;

import com.minsoo.co.tireerp.adminserver.model.AddressDTO;
import com.minsoo.co.tireerp.domain.entity.management.Warehouse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class WarehouseResponse {

    private Long warehouseId;
    private String name;
    private String description;
    private AddressDTO address;

    private String createdBy;
    private LocalDateTime createdAt;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedAt;

    private WarehouseResponse(Warehouse warehouse) {
        this.warehouseId = warehouse.getId();
        this.name = warehouse.getName();
        this.description = warehouse.getDescription();
        this.address = new AddressDTO(warehouse.getAddress());

        this.createdAt = warehouse.getCreatedAt();
        this.lastModifiedAt = warehouse.getLastModifiedAt();
        this.createdBy = warehouse.getCreatedBy();
        this.lastModifiedBy = warehouse.getLastModifiedBy();
    }

    public static WarehouseResponse from(Warehouse warehouse) {
        return new WarehouseResponse(warehouse);
    }
}
