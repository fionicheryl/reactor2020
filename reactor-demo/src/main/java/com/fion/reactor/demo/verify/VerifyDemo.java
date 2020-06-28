package com.fion.reactor.demo.verify;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
public class VerifyDemo {

    @Test
    public void verify() {
        Flux<Integer> flux = Flux.range(1, 5);
        flux.subscribe(v -> System.out.println("-> " + v));

        StepVerifier.create(flux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3, 4, 5)
                .expectComplete()
                .verify();
    }
}
