package com.example.istaskpeople.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(name = "addr_type", table = "t_addresses")
    private String addressType;

    @Column(name = "addr_info", table = "t_addresses")
    private String addressInfo;

}
