package com.fion.reactor.demo.backpressure;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class BackPressureDemo {

    @Test
    public void backPressure() {
        Flux<Long> flux = Flux.interval(Duration.ofMillis(1));

        flux.subscribe(new Subscriber<Long>() {
            Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("val: " + aLong);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
