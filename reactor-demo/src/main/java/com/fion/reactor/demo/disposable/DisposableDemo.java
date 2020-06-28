package com.fion.reactor.demo.disposable;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DisposableDemo {

    @Test
    public void disposable() throws InterruptedException {
        Flux<Long> flux = Flux.interval(Duration.ofMillis(1));
        Disposable disposable = flux.subscribe(v -> log.info(" {}", v));

        TimeUnit.MILLISECONDS.sleep(100);
        disposable.dispose();
        log.info("stop");
    }
}
