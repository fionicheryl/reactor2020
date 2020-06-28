package com.fion.reactor.demo.buffer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class BufferDemo {

    @Test
    public void buffer() {
        Flux<Integer> flux = Flux.range(1, 10);
        Flux<List<Integer>> listFlux = flux.buffer();
        listFlux.subscribe(v -> System.out.print(" " + v));

        System.out.println();

        Flux<List<Integer>> listFlux2 = flux.buffer(2);
        listFlux2.subscribe(v -> System.out.print(" " + v));
    }
}
