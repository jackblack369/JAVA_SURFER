package com.concurrent;

/**
 * 功能描述 :   newScheduledThreadPool(int corePoolSize)
 * 返回一个SchededExecutorService对象，但该线程池可以设置线程的数量，支持定时及周期性任务执行。
 * 这个线程池主要用来延迟执行任务或者定期执行任务。
 * <pre> {@code
 *  public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
 *     return new ScheduledThreadPoolExecutor(corePoolSize);
 *  }
 *  }
 *  </pre>
 *
 * 返回的是ScheduledThreadPoolExecutor对象
 * <pre>{@code
 * public ScheduledThreadPoolExecutor(int corePoolSize) {
 *     super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
 *           new DelayedWorkQueue());
 * }
 * }</pre>
 * @author dongwei
 * 2018/6/14 15:59
 **/
public class NewScheduledThreadPool {
}
