package com.fion.reactor.demo.zip;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

@Slf4j
public class ZipWithDemo {

    private Flux<String> menFlux;
    private Flux<String> womenFlux;
    private Flux<Boolean> successFlux;

    @Before
    public void before() {
        menFlux = Flux.just("fion", "messi", "nona", "peter", "lufy");
        womenFlux = Flux.just("cheryl", "andona", "elina", "julia");
        successFlux = Flux.just(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
    }

    @Test
    public void zipWith() {
        menFlux.zipWith(womenFlux).subscribe(System.out::println);
    }

    @Test
    public void zip2() {
        Flux<Tuple2<String, String>> tuple2Flux = Flux.zip(womenFlux, menFlux);
        tuple2Flux.subscribe(System.out::println);
    }

    @Test
    public void zip3() {
        Flux<Tuple3<String, String, Boolean>> tuple3Flux = Flux.zip(womenFlux, menFlux, successFlux);
        tuple3Flux.subscribe(System.out::println);
    }
}
