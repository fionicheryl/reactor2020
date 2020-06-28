package com.fion.reactor.demo.concatmap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ConcatMapDemo {

    @Test
    public void concatMap() throws InterruptedException {
        Flux<String> wordFlux = Flux.just("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n");
        wordFlux.window(3)
                .flatMap(flux -> flux.map(String::toUpperCase))
                .delayElements(Duration.ofMillis(200))
                .subscribe(x -> System.out.print(x + " "));

        TimeUnit.SECONDS.sleep(5);
        System.out.println();

        wordFlux.window(3)
                .concatMap(flux -> flux.map(String::toUpperCase))
                .delayElements(Duration.ofMillis(200))
                .subscribe(x -> System.out.print(x + " "));

        TimeUnit.SECONDS.sleep(5);
    }
}
