package com.fion.reactor.demo.flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class FluxDemo {

    @Test
    public void flux() {
        StringBuilder sb = new StringBuilder();

        Flux<String> strFlux = Flux.just("fion", "cheryl", "lufy");
        strFlux.subscribe(x -> sb.append(x).append(" "));
        log.info("[Build flux from object] strFlux = {}", sb.toString());

        sb.delete(0, sb.length());

        Flux<Integer> listFlux = Flux.fromIterable(Arrays.asList(1, 2, 3, 4, 5));
        listFlux.subscribe(x -> sb.append(x).append(" "));
        log.info("[Build flux from list] listFlux = {}", sb.toString());

        sb.delete(0, sb.length());

        Flux<Integer> rangeFlux = Flux.range(1, 10);
        rangeFlux.subscribe(x -> sb.append(x).append(" "));
        log.info("[Build flux from range] rangeFlux = {}", sb.toString());

        sb.delete(0, sb.length());

        Flux<Integer> fluxFlux = Flux.from(rangeFlux);
        fluxFlux.subscribe(x -> sb.append(x).append(" "));
        log.info("[Build flux from flux] fluxFlux = {}", sb.toString());
    }

}
