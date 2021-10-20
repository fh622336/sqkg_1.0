package com.kingdee.sqkg.comfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
//    }

//    /**
//     * Callback allowing a {@link
//     * } and specific {@link  Task}
//     * instances to be registered against the given the {@link ScheduledTaskRegistrar}
//     *
//     * @param taskRegistrar the registrar to be configured.
//     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        taskScheduler.setPoolSize(20);
//        taskScheduler.setThreadNamePrefix("taskExecutor-");
//        taskScheduler.initialize();
//        taskScheduler.setAwaitTerminationSeconds(300);
        final   ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("taskExecutor-"); //设置线程名开头
        scheduler.initialize();
        scheduler.setAwaitTerminationSeconds(120);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        taskRegistrar.setTaskScheduler(scheduler);
    }
}
