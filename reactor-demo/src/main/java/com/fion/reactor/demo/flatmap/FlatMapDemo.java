package com.fion.reactor.demo.flatmap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FlatMapDemo {

    @Test
    public void flatMap() {
        StringBuilder consumer = new StringBuilder();

        Flux<Integer> flux = Flux.range(1, 20);
        Flux<Flux<Integer>> flux2 = flux.window(3);

        /**
         * 相当于flatMap将原二维的flux流降维了，成了一维flux流
         */
        Flux<Integer> flux3 = flux2.flatMap(f -> f);
        flux3.subscribe(v -> consumer.append(v).append(" "));
        log.info("[flat map flux] after faltMap, consumer = {}", consumer.toString());

        consumer.delete(0, consumer.length());

        List<Flux<Integer>> fluxList = new ArrayList<>();
        flux2.subscribe(v -> fluxList.add(v), null, null, subscription -> subscription.request(1));
        fluxList.get(0).subscribe(v -> consumer.append(v).append(" "));
        log.info("[flat map flux] fluxList[0], consumer = {}", consumer.toString());
    }
}
