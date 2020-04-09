package com.currency.example.syncContainer;

import com.currency.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * java.lang.ArrayIndexOutOfBoundsException: Array index out of rang
 *
 */
@NotThreadSafe
public class VectorExample2 {

    private static final Vector<Integer> vector = new Vector<Integer>();


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                vector.remove(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                vector.get(i);
            }
        });
        thread1.start();
        thread2.start();
    }

}
