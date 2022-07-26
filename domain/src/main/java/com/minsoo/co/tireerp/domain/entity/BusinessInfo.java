package com.minsoo.co.tireerp.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class BusinessInfo {

    @Column(name = "business_number")
    private String businessNumber;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "business_type")
    private String businessType;

    @Embedded
    private Address address;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "representative")
    private String representative;

    @Column(name = "representative_phone_number")
    private String representativePhoneNumber;

    @Column(name = "manager")
    private String manager;

    @Column(name = "manager_phone_number")
    private String managerPhoneNumber;
}
