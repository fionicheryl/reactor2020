package com.fion.reactor.demo.map;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MapDemo {

    private List<Employee> employees;

    private BigDecimal leaderSalaryLine;

    @Before
    public void before() {
        employees = new ArrayList<>();
        employees.add(Employee.builder().id(1).name("fion").salary(new BigDecimal("20000")).build());
        employees.add(Employee.builder().id(2).name("cheryl").salary(new BigDecimal("30000")).build());
        employees.add(Employee.builder().id(3).name("lufy").salary(new BigDecimal("18000")).build());
        employees.add(Employee.builder().id(4).name("messi").salary(new BigDecimal("25000")).build());

        leaderSalaryLine = new BigDecimal("20000");
    }

    @Test
    public void map() {
        StringBuilder consumer = new StringBuilder();
        Flux<Employee> empFlux = Flux.fromIterable(employees);
        empFlux.filter(v -> leaderSalaryLine.compareTo(v.getSalary()) < 0)
                .map(v -> {
                    Leader leader = new Leader();
                    BeanUtil.copyProperties(v, leader);
                    return leader;
                })
                .subscribe(v -> consumer.append(v.getName()).append(" and "));
        consumer.delete(consumer.length() - " and ".length(), consumer.length());
        log.info("[map flux] leader is {}", consumer.toString());
    }

    @Test
    public void log() {
        Flux<Employee> empFlux = Flux.fromIterable(employees);
        empFlux.filter(v -> leaderSalaryLine.compareTo(v.getSalary()) < 0)
                .map(v -> {
                    Leader leader = new Leader();
                    BeanUtil.copyProperties(v, leader);
                    return leader;
                })
                .log()
                .subscribe();
    }

}
