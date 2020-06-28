package com.fion.reactor.demo.take;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class TakeDemo {

    @Test
    public void take() {
        Flux<Integer> flux = Flux.range(1, 1000);

        // 取4个数据
        flux.take(4).subscribe(System.out::println);

        // 取1毫秒的数据
        flux.take(Duration.ofMillis(10)).subscribe(System.out::println);

        // 取数据，直到条件被满足才停止
        flux.takeUntil(v -> 10 == v).subscribe(System.out::println);

    }
}
