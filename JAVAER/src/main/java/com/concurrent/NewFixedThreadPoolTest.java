package com.concurrent;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述 :
 * newFixedThreadPool(int nThreads)<br/>
 * 创建一个线程的线程池，若空闲则执行，若没有空闲线程则暂缓在任务队列中。<br/>
 * 该方法创建指定线程数量的线程池，没有限制可存放的线程数量（无界队列），适用于线程任务执行较快的场景。
 *
 * <pre>{@code
 * public static ExecutorService newFixedThreadPool(int nThreads) {
 *     return new ThreadPoolExecutor(nThreads, nThreads,
 *                                   0L, TimeUnit.MILLISECONDS,
 *                                   new LinkedBlockingQueue<Runnable>());
 * }
 * }</pre>
 *  <p>
 *      可以看到返回的是一个ThreadPoolExecutor对象，核心线程数和是最大线程数都是传入的参数，
 *      存活时间是0，时间单位是毫秒，阻塞队列是无界队列LinkedBlockingQueue。
 *  </p>
 * @author dongwei
 * 2018/6/14 14:32
 **/
public class NewFixedThreadPoolTest {
    public static void test1() {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        while (true) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * lambda表达式替换
         */
//        while(true){
//            pool.submit(()-> {
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }

    }


    public static void test2() {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 8; i++) {
            int finalI = i + 1;
            pool.submit(() -> {
                try {
                    System.out.println("任务" + finalI + ":开始等待2秒,时间:" + LocalTime.now() + ",当前线程名：" + Thread.currentThread().getName());
                    Thread.sleep(2000);
                    System.out.println("任务" + finalI + ":结束等待2秒,时间:" + LocalTime.now() + ",当前线程名：" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        pool.shutdown();
    }

    public static void main(String[] args) {
        test2();
    }
}
