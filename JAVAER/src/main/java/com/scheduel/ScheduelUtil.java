package com.scheduel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * 与Timer很类似，但它的效果更好，多线程并行处理定时任务时，
 * Timer运行多个TimeTask时，只要其中有一个因任务报错没有捕获抛出的异常，
 * 其它任务便会自动终止运行，使用 ScheduledExecutorService 则可以规避这个问题
 *
 *
 * @author dongwei
 * 2018/8/22 15:33
 **/
public class ScheduelUtil {
    //满足并行要求的定时调度线程池
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    //ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(new WorkRunnable(),5 ,TimeUnit.MINUTES);

    /**
     * 进行获取图片文件的执行线程
     */
    private class WorkRunnable implements Runnable {
        @Override
        public void run() {
//            try {
//                List<byte[]> findFiles = ImageUtil.getBytePicLimitTime( System.currentTimeMillis(), 5, IMGPATH);
//                setPicsWork(findFiles);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
