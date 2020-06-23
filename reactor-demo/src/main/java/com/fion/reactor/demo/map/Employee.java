package com.fion.reactor.demo.map;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Employee {

    private Integer id;

    private String name;

    private BigDecimal salary;
}
