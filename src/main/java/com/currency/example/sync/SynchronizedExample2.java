package com.currency.example.sync;

import com.currency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchroized 修饰静态代码块和class 类锁
 */
@Slf4j
@ThreadSafe
public class SynchronizedExample2 {

    /**
     * 修饰静态代码块
     */
    public static void test1(int j) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} , {}", j, i);
            }
        }
    }

    /**
     * 修饰静态代码块
     */
    public static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {} , {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            SynchronizedExample2.test1(1);
        });
        executorService.execute(() -> {
            SynchronizedExample2.test2(2);
        });

    }
}
