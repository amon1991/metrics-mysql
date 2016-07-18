package com.amon.personshare.metrics_mysql;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * Meters用来度量某个时间段的平均处理次数（request per second），
 * 每1、5、15分钟的TPS。比如一个service的请求数，通过metrics.meter()实例化一个Meter之后，
 * 然后通过meter.mark()方法就能将本次请求记录下来。统计结果有总的请求数，平均每秒的请求数，
 * 以及最近的1、5、15分钟的平均TPS。
 * Created by Administrator on 2015/11/4.
 */
public class MetersTest {

    /**
     * 实例化一个Meter
     */
    private static final Meter requests =
            MetricsInstance.getMetricRegistryInstance().
                    meter(MetricRegistry.name(MetersTest.class, "request"));

    public static void handleRequest() {
        requests.mark();
    }


    public static void main(String[] args) throws Exception{

        MetricsInstance.getMysqlReporterInstance().start(1, TimeUnit.SECONDS);

        for (int i = 0; i < 20; i++) {
            MetersTest.handleRequest();
            Thread.sleep(100);
        }

        for (int i = 0; i < 20; i++) {
            MetersTest.handleRequest();
            Thread.sleep(200);
        }

        for (int i = 0; i < 20; i++) {
            MetersTest.handleRequest();
            Thread.sleep(500);
        }

    }


}
