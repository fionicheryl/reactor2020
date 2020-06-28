package com.fion.reactor.demo.window;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

@Slf4j
public class WindowDemo {

    @Test
    public void window() {

        Flux<Integer> flux = Flux.range(1, 100);

        Flux<Flux<Integer>> flux2Line = flux.window(2);
        flux2Line.count().subscribe(System.out::println);

        Flux<Flux<Integer>> flux5Line = flux.window(5);
        flux5Line.count().subscribe(System.out::println);

        Flux<Flux<Flux<Integer>>> _3flux5Line = flux.window(2).window(2);
        _3flux5Line.count().subscribe(System.out::println);
    }
}
