package com.jdsbbmq.wjxbx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ExecutorConfig {
    @Value("${async.executor.thread.core_pool_size}")
    private int corePoolSize; // 核心线程数
    @Value("${async.executor.thread.max_pool_size}")
    private int maxPoolSize; // 最大线程数
    @Value("${async.executor.thread.queue_capacity}")
    private int queueCapacity; // 队列容量
    @Value("${async.executor.thread.name_prefix}")
    private String namePrefix; // 线程名称前缀

    /**
     * 配置异步任务执行器
     *
     * @return 异步任务执行器
     */
    @Bean(name = "asyncServiceExecutor")
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 配置队列大小
        executor.setQueueCapacity(queueCapacity);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(namePrefix);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * 配置异步任务执行器
     *
     * @return 异步任务执行器
     */
    @Bean(name = "asyncServiceExecutor2")
    public Executor asyncServiceExecutor2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数
        // 核心线程数
        int corePoolSizeForResource = 16;
        executor.setCorePoolSize(corePoolSizeForResource);
        // 配置最大线程数
        // 最大线程数
        int maxPoolSizeForResource = 32;
        executor.setMaxPoolSize(maxPoolSizeForResource);
        // 配置队列大小
        // 队列容量
        int queueCapacityForResource = 256;
        executor.setQueueCapacity(queueCapacityForResource);
        // 配置线程池中的线程的名称前缀
        // 线程名称前缀
        String namePrefixForResource = "resource-pool-";
        executor.setThreadNamePrefix(namePrefixForResource);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 执行初始化
        executor.initialize();
        return executor;
    }
}