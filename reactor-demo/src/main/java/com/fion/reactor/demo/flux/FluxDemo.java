package com.fion.reactor.demo.flux;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
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

    @Test
    public void subscribe() {
        Flux<String> strFlux = Flux.just("fion", "cheryl", "lufy");

        StringBuilder consumer = new StringBuilder();
        strFlux.subscribe(x -> consumer.append(x).append(" "));
        log.info("[Subscribe flux] consumer flux = {}", consumer.toString());

        consumer.delete(0, consumer.length());

        StringBuilder errorConsumer = new StringBuilder();
        strFlux.subscribe(x -> consumer.append(x).append(" "), t -> errorConsumer.append(t.getMessage()));
        log.info("[Subscribe flux] consumer flux = {}, errorConsumer flux = {}", consumer.toString(), errorConsumer.toString());

        consumer.delete(0, consumer.length());
        errorConsumer.delete(0, errorConsumer.length());

        StringBuilder completeConsumer = new StringBuilder();
        strFlux.subscribe(x -> consumer.append(x).append(" "),
                t -> errorConsumer.append(t.getMessage()),
                () -> completeConsumer.append("consume complete"));
        log.info("[Subscribe flux] consumer flux = {}, errorConsumer flux = {}, completeConsumer = {}", consumer.toString(), errorConsumer.toString(), completeConsumer);

        consumer.delete(0, consumer.length());
        completeConsumer.delete(0, completeConsumer.length());

        strFlux.subscribe(x -> consumer.append(x).append(" "),
                null,
                () -> completeConsumer.append("consume complete"),
                subscription -> subscription.request(2));
        log.info("[Subscribe flux] request of subscription is 2, then consumer flux = {}, completeConsumer = {}", consumer.toString(), completeConsumer.toString());
    }

    @Test
    public void subscriber() {
        StringBuilder consumer = new StringBuilder();
        StringBuilder errorConsumer = new StringBuilder();
        StringBuilder completeConsumer = new StringBuilder();
        Flux.range(1, 5).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                if (4 == integer) {
                    throw new IllegalArgumentException("error for 4");
                }
                consumer.append(integer).append(" ");
            }

            @Override
            public void onError(Throwable throwable) {
                errorConsumer.append(throwable.getMessage());
            }

            @Override
            public void onComplete() {
                completeConsumer.append("consume complete");
            }
        });
        log.info("[Subscriber flux] consumer = {}, errorConsumer = {}, completeConsumer = {}", consumer.toString(), errorConsumer.toString(), completeConsumer.toString());
    }

}
