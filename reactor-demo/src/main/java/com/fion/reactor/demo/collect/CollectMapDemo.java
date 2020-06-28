package com.fion.reactor.demo.collect;

import com.fion.reactor.demo.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Slf4j
public class CollectMapDemo {

    private List<Employee> employees;

    private List<Employee> redundantEmployees;

    @Before
    public void before() {
        employees = new ArrayList<>();
        employees.add(Employee.builder().id(1).name("fion").salary(new BigDecimal("20000")).build());
        employees.add(Employee.builder().id(2).name("cheryl").salary(new BigDecimal("30000")).build());
        employees.add(Employee.builder().id(3).name("lufy").salary(new BigDecimal("18000")).build());
        employees.add(Employee.builder().id(4).name("messi").salary(new BigDecimal("25000")).build());

        redundantEmployees = new ArrayList<>();
        redundantEmployees.add(Employee.builder().id(1).name("fion").salary(new BigDecimal("20000")).build());
        // redundant employee
        redundantEmployees.add(Employee.builder().id(2).name("fion").salary(new BigDecimal("15000")).build());
        redundantEmployees.add(Employee.builder().id(3).name("cheryl").salary(new BigDecimal("30000")).build());
        redundantEmployees.add(Employee.builder().id(4).name("lufy").salary(new BigDecimal("18000")).build());
        redundantEmployees.add(Employee.builder().id(5).name("messi").salary(new BigDecimal("25000")).build());
    }

    @Test
    public void collectMap() {
        Flux<Employee> employeeFlux = Flux.fromIterable(employees);
        Mono<Map<String, Employee>> mapMono = employeeFlux.collectMap(Employee::getName, v -> v);
        mapMono.subscribe(System.out::println);
    }

    @Test
    public void collectMapConflict() {
        Flux<Employee> employeeFlux = Flux.fromIterable(redundantEmployees);
        // 重复key，取了后者
        Mono<Map<String, Employee>> mapMono = employeeFlux.collectMap(Employee::getName, v -> v);
        mapMono.subscribe(System.out::println);
    }
}
