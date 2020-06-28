package com.fion.reactor.demo.parallel;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ParallelDemo {

    @Test
    public void parallel() {

        // 都在主线程上订阅
        Flux.range(1, 20).subscribe(v -> log.info("-> {}", v));

        log.info("==============After Parallel================");

        // 仅仅加parallel并没有用
        Flux.range(1, 20).parallel().subscribe(v -> log.info("-> {}", v));

        log.info("==============After Run On================");

        // 调度并行计算
        Flux.range(1, 20).parallel().runOn(Schedulers.parallel()).subscribe(v -> log.info("-> {}", v));

        log.info("==============After Parallel Count================");

        // 调度并行计算
        Flux.range(1, 20).parallel(3).runOn(Schedulers.parallel()).subscribe(v -> log.info("-> {}", v));
    }
}
