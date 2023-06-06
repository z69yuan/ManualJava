package com.beancurd.rxjava.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Dispatchers {
    public static ExecutorService IO = Executors.newFixedThreadPool(4, new ThreadFactory() {

        private int id = 0;
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("IO-" + id++);
            return t;
        }
    });
}
