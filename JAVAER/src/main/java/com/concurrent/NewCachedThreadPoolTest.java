package com.concurrent;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述 :   newCachedThreadPool()
 * <p>返回一个可根据实际情况调整线程个数的线程池，不限制最大线程数量，<br/>
 * 若有空闲的线程则执行任务，若无任务则不创建线程，并且每一个空闲线程会在60秒后自动回收。
 *
 * <p>newCachedThreadPool方法创建的线程池会根据需要自动创建新线程
 * 但是！在newCachedThreadPool方法中的空闲线程存活时间时60秒，一旦超过60秒线程就会被终止.
 * 这边还隐含了一个问题，如果执行的线程较慢，而提交任务的速度快于线程执行的速度,
 * 那么就会不断的创建新的线程，从而导致cpu和内存的增长。
 *
 * <pre>{@code
 *  public static ExecutorService newCachedThreadPool() {
 *     return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
 *                                   60L, TimeUnit.SECONDS,
 *                                   new SynchronousQueue<Runnable>());
 * }
 *  }</pre>
 *
 * @author dongwei
 * 2018/6/14 15:34
 **/
public class NewCachedThreadPoolTest {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 8; i++) {
            int finalI = i + 1;
            pool.submit(() -> {
                try {
                    System.out.println("任务" + finalI + ":开始等待60秒,时间:" + LocalTime.now() + ",当前线程名：" + Thread.currentThread().getName());
                    Thread.sleep(60000);
                    System.out.println("任务" + finalI + ":结束等待60秒,时间:" + LocalTime.now() + ",当前线程名：" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            //睡眠10秒
            Thread.sleep(10000);
        }
        pool.shutdown();
    }
}
