package com.fion.reactor.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

@Slf4j
public class ExceptionDemo {

    @Test
    public void errorContinue() {
        Flux.range(-2, 5)
                .map(v -> 10 / v)
                .onErrorContinue((t, v) -> {
                    log.info("[error continue] consume error, value = {}, error message = {}", v, t.getMessage());
                })
                .subscribe(System.out::println);
    }

    @Test
    public void errorResume() {
        Flux.range(-2, 5)
                .map(v -> 10 / v)
                .onErrorResume(fallback -> {
                    log.info("[error resume] consume error, fallback message = {}", fallback.getMessage());
                    return Flux.range(5, 10);
                })
                .subscribe(System.out::println);
    }

}
