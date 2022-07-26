package com.minsoo.co.tireerp.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class Address {

    // 도시
    @Column(name = "city")
    private String city;

    // 도로명 주소
    @Column(name = "street_address")
    private String streetAddress;

    // 상세 주소
    @Column(name = "detail_address")
    private String detailAddress;

    // 우편번호
    @Column(name = "zip_code")
    private String zipCode;
}
