package com.currency.example.syncContainer;

import com.currency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class CollectionsExample {
    private static int clientTotal = 5000;
    private static int threadTotal = 200;
    private static int count = 0;
    private static Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {

            final int val = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(val);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception:", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("count:{}", map.size());
        executorService.shutdown();
    }

    private static void add(int i) {
        map.put(i, i);
    }
}
