package com.amon.personshare.metrics_mysql;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Gauges是一个最简单的计量，一般用来统计瞬时状态的数据信息，比如系统中处于pending状态的job。
 * Created by cym on 2015/11/4.
 */
public class GaugesTest {

    private final Queue queue;

    public GaugesTest(String name) {

        this.queue = new LinkedList();
        MetricsInstance.getMetricRegistryInstance()
                .register(MetricRegistry.name(GaugesTest.class, name, "size"),
                        new Gauge<Integer>() {
                            @Override
                            public Integer getValue() {
                                return queue.size();
                            }
                        });
    }

    public void add(){
        queue.add("test");
    }

    public void pool(){
        queue.poll();
    }

    public static void main(String[] args) throws Exception{

        MetricsInstance.getMysqlReporterInstance().start(1, TimeUnit.SECONDS);

        GaugesTest gaugesTest = new GaugesTest("job");

        int timeIndex = 1;

        for (int i = 0; i < 10; i++) {
            gaugesTest.add();
            Thread.sleep(1000);
            System.out.println("usetime:"+(timeIndex++)+"s");
        }

        for (int i = 0; i < 10; i++) {
            gaugesTest.pool();
            Thread.sleep(1000);
            System.out.println("usetime:"+(timeIndex++)+"s");
        }

    }


}
