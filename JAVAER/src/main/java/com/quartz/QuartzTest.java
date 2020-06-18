package com.quartz;

/**
 * import static静态导入是JDK1.5中的新特性。<br/>
 * 一般我们导入一个类都用 import com.....ClassName;而静态导入是这样：import static com.....ClassName.*;
 * 这里的多了个static，还有就是类名ClassName后面多了个 .* ，意思是导入这个类里的静态方法。
 * 当然，也可以只导入某个静态方法，只要把 .* 换成静态方法名就行了。然后在这个类中，就可以直接用方法名调用静态方法，而不必用ClassName.方法名 的方式来调用。
 * 这种方法的好处就是可以简化一些操作，例如打印操作System.out.println(...);就可以将其写入一个静态方法print(...)，在使用时直接print(...)就可以了。
 * 但是这种方法建议在有很多重复调用的时候使用，如果仅有一到两次调用，不如直接写来的方便。
 */

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {

    /**
     * 要定义一个任务，需要干几件事：
     * 1,创建一个org.quartz.Job的实现类，并实现实现自己的业务逻辑。比如上面的DoNothingJob。
     * 2,定义一个JobDetail，引用这个实现类
     * 3,加入scheduleJob
     */
    public static void main(String[] args) {
        try {
            //创建scheduler(调度器，所有的调度都是由它控制)
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //定义一个Trigger（定义触发的条件）
            Trigger trigger = newTrigger().withIdentity("trigger1", "group1") //定义name/group
                    .startNow()//一旦加入scheduler，立即生效
                    .withSchedule(simpleSchedule() //使用SimpleTrigger
                            .withIntervalInSeconds(1) //每隔一秒执行一次
                            .repeatForever()) //一直执行，奔腾到老不停歇
                    .build();

            /**
             * JobDetail 定义的是任务数据，而真正的执行逻辑是在Job中，例子中是HelloQuartz。<br/>
             * 为什么设计成JobDetail + Job，不直接使用Job？<br/>
             * 这是因为任务是有可能并发执行，如果Scheduler直接使用Job，就会存在对同一个Job实例并发访问的问题。<br/>
             * 而JobDetail & Job 方式，sheduler每次执行，都会根据JobDetail创建一个新的Job实例，这样就可以规避并发访问的问题。
             */
            JobDetail job = newJob(HelloQuartz.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                    .withIdentity("job1", "group1") //定义name/group
                    .usingJobData("name", "quartz") //定义属性
                    .build();

            //加入这个调度
            scheduler.scheduleJob(job, trigger);

            //启动之
            scheduler.start();

            //运行一段时间后关闭
            Thread.sleep(10000);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * JobDetail和Trigger都有name和group。
     *
     * name是它们在这个sheduler里面的唯一标识。如果我们要更新一个JobDetail定义，只需要设置一个name相同的JobDetail实例即可。
     *
     * group是一个组织单元，sheduler会提供一些对整组操作的API，比如 scheduler.resumeJobs()。
     */
}
