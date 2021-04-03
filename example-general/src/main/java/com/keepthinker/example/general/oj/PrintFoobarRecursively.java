package com.keepthinker.example.general.oj;

import java.util.concurrent.CountDownLatch;

public class PrintFoobarRecursively {

    class FooBar {

        private volatile CountDownLatch countDownLatch1 = new CountDownLatch(1);
        private volatile CountDownLatch countDownLatch2 = new CountDownLatch(1);
        private int n;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            countDownLatch2.countDown();
            for (int i = 0; i < n; i++) {
                countDownLatch2.await();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                countDownLatch2 = new CountDownLatch(1);
                countDownLatch1.countDown();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                countDownLatch1.await();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                countDownLatch1 = new CountDownLatch(1);
                countDownLatch2.countDown();
            }
        }
    }
}
