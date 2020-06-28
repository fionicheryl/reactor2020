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

@Slf4j
public class CollectDemo {

    private List<Employee> employees;

    @Before
    public void before() {
        employees = new ArrayList<>();
        employees.add(Employee.builder().id(1).name("fion").salary(new BigDecimal("20000")).build());
        employees.add(Employee.builder().id(2).name("cheryl").salary(new BigDecimal("30000")).build());
        employees.add(Employee.builder().id(3).name("lufy").salary(new BigDecimal("18000")).build());
        employees.add(Employee.builder().id(4).name("messi").salary(new BigDecimal("25000")).build());
    }

    @Test
    public void collect() {
        Flux<Integer> flux = Flux.range(1, 5);
        Mono<List<Integer>> mono = flux.collectList();
        mono.subscribe(System.out::println);

        Flux<Employee> employeeFlux = Flux.fromIterable(employees);
        Mono<List<Employee>> employeeMono = employeeFlux.collectSortedList(Comparator.comparing(Employee::getSalary));
        employeeMono.subscribe(System.out::println);
    }
}
