package com.fion.reactor.demo.mono;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class MonoDemo {

    @Test
    public void mono() {

        StringBuilder sb = new StringBuilder();

        Mono<String> strMono = Mono.just("fion");
        strMono.subscribe(x -> sb.append(x).append(" "));
        log.info("[Build mono from object] strMono = {}", sb.toString());

        sb.delete(0, sb.length());

        Mono<String> callMono = Mono.fromCallable(() -> "call");
        callMono.subscribe(x -> sb.append(x).append(" "));
        log.info("[Build mono from callable] callMono = {}", sb.toString());

        sb.delete(0, sb.length());

        Mono<String> futureMono = Mono.fromFuture(CompletableFuture.completedFuture("future"));
        futureMono.subscribe(x -> sb.append(x).append(" "));
        log.info("[Build mono from future] futureMono = {}", sb.toString());

        sb.delete(0, sb.length());

        Mono<String> supplierMono = Mono.fromSupplier(MonoDemo::supplier);
        supplierMono.subscribe(x -> sb.append(x).append(" "));
        log.info("[Build mono from supplier] supplierMono = {}", sb.toString());

        sb.delete(0, sb.length());

        Mono<String> monoMono = Mono.from(Mono.just("mono"));
        monoMono.subscribe(x -> sb.append(x).append(" "));
        log.info("[Build mono from mono] monoMono = {}", sb.toString());

        sb.delete(0, sb.length());

        Mono<Integer> fluxMono = Mono.from(Flux.range(1, 5));
        fluxMono.subscribe(x -> sb.append(x).append(" "));
        log.info("[Build mono from flux] fluxMono = {}", sb.toString());
    }

    private static String supplier() {
        return "supplier";
    }
}
