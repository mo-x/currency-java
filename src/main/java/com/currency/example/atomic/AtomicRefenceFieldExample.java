package com.currency.example.atomic;

import com.currency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicRefenceFieldExample {

    private static AtomicIntegerFieldUpdater<AtomicRefenceFieldExample> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicRefenceFieldExample.class, "count");

    @Getter
    public volatile int count = 100;


    public static void main(String[] args) {
        AtomicRefenceFieldExample example = new AtomicRefenceFieldExample();
        if (updater.compareAndSet(example, 100, 120)) {
            log.info("update success1,{}", example.getCount());
        }
        if (updater.compareAndSet(example, 100, 120)) {
            log.info("update success2,count{}", example.getCount());
        } else {
            log.info("update failed ,{}", example.getCount());
        }
    }
}
