package com.keepthinker.example.general.concurrency.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by keepthinker on 2017/4/28.
 */
public class ConcurrentUtils {

    private static ExecutorService executor = new ThreadPoolExecutor(10,
            15,
            10l,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>(), new ExceptionLogThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void execute(Runnable runnable){
        executor.execute(runnable);
    }

    public static void submit(Callable callable){
        executor.submit(callable);
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
                System.out.format("Thread: %s, exception: %s", t.toString(), e.getMessage().toString());
            }
        }

    }


}

