package com.mybatis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hotel {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String city;

}
