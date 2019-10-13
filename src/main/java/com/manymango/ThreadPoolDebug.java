package com.manymango;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Liu Chaoxing
 * @date 2019/10/13 11:24
 */
public class ThreadPoolDebug {

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger threadNumber=new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "test-thread-pool-"+threadNumber.incrementAndGet());
            }
        };

        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        ExecutorService executorService = new ThreadPoolExecutor(1, 4, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5), threadFactory, handler);

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + "xixi");
            Thread.sleep(1000);
            return "task execute ok!";
        };

        executorService.submit(callable);

        for (int i=0; i<10; ++i) {
            try {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (true) {
            int a = 1;
        }

//        executorService.shutdown();
    }
}
