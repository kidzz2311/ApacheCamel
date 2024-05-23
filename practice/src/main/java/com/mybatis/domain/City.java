package com.mybatis.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class City implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String state;

  private String country;

}
