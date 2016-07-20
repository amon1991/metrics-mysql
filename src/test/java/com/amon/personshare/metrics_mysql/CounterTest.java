package com.amon.personshare.metrics_mysql;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Counter是Gauge的一个特例，维护一个计数器，可以通过inc()和dec()方法对计数器做修改。
 * 使用步骤与Gauge基本类似，在MetricRegistry中提供了静态方法可以直接实例化一个Counter。
 * Created by cym on 2015/11/4.
 */
public class CounterTest {

    private static Counter pendingJobs =
            MetricsInstance.getMetricRegistryInstance()
                    .counter(MetricRegistry.name(CounterTest.class, "pedding-jobs_3"));

    private static Queue<String> queue = new LinkedList<String>();

    public static void add(String str) {
        pendingJobs.inc();
        queue.offer(str);
    }

    public String take() {
        pendingJobs.dec();
        return queue.poll();
    }

    public static void main(String[] args) throws Exception{

        MetricsInstance.getMysqlReporterInstance().start(1, TimeUnit.SECONDS);

        CounterTest counterTest = new CounterTest();

        for (int i = 0; i < 1000; i++) {
            counterTest.add(i+"");
            counterTest.add(i+"");
            Thread.sleep(1000);
            System.out.println("insert...");
        }

    }

}
