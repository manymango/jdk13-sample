package com.manymango;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author manymango
 * @date 2019/10/13 11:24
 */
public class ThreadPoolDebug {

    public static void main(String[] args) {
        // 申明一个线程工厂，创建线程的名称是test-thread-pool-加number
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger threadNumber=new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "test-thread-pool-"+threadNumber.incrementAndGet());
            }
        };
        // 使用AbortPolicy拒绝策略
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        ExecutorService executorService = new ThreadPoolExecutor(1, 4, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5), threadFactory, handler);

        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + " , I'm callable task");
            Thread.sleep(1000);
            return "task execute ok!";
        };
        try {
            // 提交一个有返回结果的异步任务
            String callableResult =  executorService.submit(callable).get();
            System.out.println(Thread.currentThread().getName()+ " " + callableResult);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 循环提交10个异步任务
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
        //关闭线程池
        executorService.shutdown();
    }
}
