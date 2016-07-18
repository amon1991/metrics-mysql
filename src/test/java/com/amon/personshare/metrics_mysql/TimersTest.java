package com.amon.personshare.metrics_mysql;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Timers主要是用来统计某一块代码段的执行时间以及其分布情况，
 * 具体是基于Histograms和Meters来实现的。样例代码如下：
 * Created by Administrator on 2015/11/4.
 */
public class TimersTest {

    /**
     * 实例化一个Timer
     */
    private static final Timer requests =
            MetricsInstance.getMetricRegistryInstance()
                    .timer(MetricRegistry.name(TimersTest.class, "request"));


    public static void handleRequest(int sleep) {
        Timer.Context context = requests.time();
        try {
            //some operator
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.stop();
        }
    }

    public static void main(String[] args) {

        MetricsInstance.getMysqlReporterInstance().start(1, TimeUnit.SECONDS);

        Random random = new Random();
        while(true){
            handleRequest(random.nextInt(1000));
        }

    }

}
