package com.keepthinker.example.general.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Craig, Landin, and Hagersten locks implementation
 * Created by keepthinker on 2017/12/23.
 */
public class CLHLock {

    private AtomicReference<QNode> tail = new AtomicReference<>(new QNode());
    private Map<Thread, QNode> map = new ConcurrentHashMap<>();

    public void lock(){
        QNode qNode = new QNode();
        map.put(Thread.currentThread(), qNode);
        qNode.setLock(true);
        QNode pre = tail.getAndSet(qNode); //当前线程栈已经锁定前驱QNode
        while(pre.isLock());
    }

    public void release(){
        QNode qNode = map.get(Thread.currentThread());
        qNode.setLock(false);
        map.remove(Thread.currentThread());

    }

    private static class QNode{
        private volatile boolean lock;

        public boolean isLock() {
            return lock;
        }

        public void setLock(boolean lock) {
            this.lock = lock;
        }
    }


}
