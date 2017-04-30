package com.keepthinker.example.general.concurrency.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by keepthinker on 2017/4/28.
 */
public class ConcurrentUtils {

    private final static Logger LOGGER = LogManager.getLogger(ConcurrentUtils.class);

    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 15;
    private static final int MAX_WORK_QUEUE_SIZE = 1000000;

    private static final BlockingQueue<Runnable> BLOCKING_QUEUE = new LinkedBlockingQueue<Runnable>(MAX_WORK_QUEUE_SIZE);

    private static ExecutorService executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE,
            10L, TimeUnit.MINUTES,
            BLOCKING_QUEUE,
            new ExceptionLogThreadFactory(),
            new AbortAndLogPolicy());

    public static void execute(Runnable runnable){
        executor.execute(runnable);
    }

    public static void submit(Callable callable){
        executor.submit(callable);
    }

    public static int workPoolSize(){
        return BLOCKING_QUEUE.size();
    }

    public boolean isAvailable(){
        return BLOCKING_QUEUE.size() < MAX_WORK_QUEUE_SIZE;
    }

    private static class AbortAndLogPolicy implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            LOGGER.error("Thread pool executor is full");
        }
    }

    private static class ExceptionLogThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public ExceptionLogThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            t.setUncaughtExceptionHandler(new UncaughtExceptionLogHandler());
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }

        private static class UncaughtExceptionLogHandler implements Thread.UncaughtExceptionHandler{

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LOGGER.error(String.format("Thread: %s", t), e);
            }
        }

    }


}

