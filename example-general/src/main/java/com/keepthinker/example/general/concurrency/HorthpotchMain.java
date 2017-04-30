package com.keepthinker.example.general.concurrency;

import com.keepthinker.example.general.concurrency.utils.RateController;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by keepthinker on 2017/4/30.
 */
public class HorthpotchMain {
    public static void main(String[] args){
//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);
//        executorService.scheduleAtFixedRate(new Thread(), 10, 1, TimeUnit.SECONDS);
        int tps = 1000;
        RateController rateController = RateController.newBuilder().setTps(tps).setClearInterval(200).build();
        int count = 0;
        for(;;){
            rateController.acquire();
            count++;
            if(count % tps == 0) {
                int milli = Calendar.getInstance().get(Calendar.MILLISECOND);
                System.out.println(milli + ":" + count);
            }
        }
    }
}
