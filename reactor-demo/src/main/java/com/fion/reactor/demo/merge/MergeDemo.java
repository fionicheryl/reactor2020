package com.fion.reactor.demo.merge;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MergeDemo {

    @Test
    public void merge() throws InterruptedException {
        Flux<Long> flux1 = Flux.interval(Duration.ofMillis(100)).take(10);
        Flux<Long> flux2 = Flux.interval(Duration.ofMillis(100)).take(10);

        System.out.println("=========merge=========");

        /*Flux<Long> flux3 = Flux.merge(flux1, flux2);
        flux3.subscribe(System.out::println);*/

        System.out.println("=========concat=========");

        Flux<Long> flux4 = Flux.concat(flux1, flux2);
        flux4.subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(5);
    }
}
