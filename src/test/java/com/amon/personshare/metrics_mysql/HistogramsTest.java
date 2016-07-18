package com.amon.personshare.metrics_mysql;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Histograms主要使用来统计数据的分布情况，
 * 最大值、最小值、平均值、中位数，百分比（75%、90%、95%、98%、99%和99.9%）。
 * 例如，需要统计某个页面的请求响应时间分布情况，可以使用该种类型的Metrics进行统计。
 * 具体的样例代码如下：
 * Created by cym on 2015/11/4.
 */
public class HistogramsTest {

    /**
     * 实例化一个Histograms
     */
    private static final Histogram randomNums = MetricsInstance.getMetricRegistryInstance()
            .histogram(MetricRegistry.name(HistogramsTest.class, "random"));

    public static void handleRequest(double random) {
        randomNums.update((int) (random*100));
    }

    public static void main(String[] args) throws Exception{

        MetricsInstance.getMysqlReporterInstance().start(1, TimeUnit.SECONDS);

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            HistogramsTest.handleRequest(rand.nextDouble());
            Thread.sleep(1000);
        }

    }
}
