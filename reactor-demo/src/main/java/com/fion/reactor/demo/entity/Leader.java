package com.fion.reactor.demo.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Leader {
    private Integer id;

    private String name;

    private BigDecimal salary;
}
