package com.keepthinker.example.nosql;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * 参考： https://cloud.tencent.com/developer/article/2223087
 */
public class RedissionMain {
    private static RedissonClient redisson;

    static {

        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        // connects to 127.0.0.1:6379 by default
        redisson = Redisson.create(config);
    }

    public static void main(String[] args) throws InterruptedException {

//        bloomFilter();
        idGenerator();
    }

    private static void lock() throws InterruptedException {


        RLock lock = redisson.getLock("lock");
        lock.lock(2, TimeUnit.SECONDS);

        Thread t = new Thread() {
            public void run() {
                RLock lock1 = redisson.getLock("lock");
                lock1.lock();
                lock1.unlock();
            };
        };

        t.start();
        t.join();

        redisson.shutdown();
    }


    private static void bloomFilter() {
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("bloomFilter");
        bloomFilter.tryInit(100_000_000, 0.03);

        bloomFilter.add("a");
        bloomFilter.add("b");
        bloomFilter.add("c");
        bloomFilter.add("d");


        System.out.println("-------------getExpectedInsertions:" + bloomFilter.getExpectedInsertions());
        System.out.println("-------------getFalseProbability:" + bloomFilter.getFalseProbability());
        System.out.println("-------------getHashIterations:" + bloomFilter.getHashIterations());


        System.out.println("------a-------contains:" + bloomFilter.contains("a"));
        System.out.println("------e-------contains:" + bloomFilter.contains("e"));

        bloomFilter.count();

        redisson.shutdown();

    }


    private static void idGenerator() {
        final String lockKey = "aaaa";
//通过redis的自增获取序号
        RAtomicLong atomicLong = redisson.getAtomicLong(lockKey);
//设置过期时间
        atomicLong.expire(30, TimeUnit.SECONDS);
// 获取值
        System.out.println(atomicLong.incrementAndGet());
        System.out.println(atomicLong.incrementAndGet());
        System.out.println(atomicLong.incrementAndGet());
        System.out.println(atomicLong.incrementAndGet());
        System.out.println(atomicLong.incrementAndGet());
    }
}
